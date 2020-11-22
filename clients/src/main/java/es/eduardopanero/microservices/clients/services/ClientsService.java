package es.eduardopanero.microservices.clients.services;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.models.Token;
import es.eduardopanero.microservices.clients.models.enums.TokenType;
import es.eduardopanero.microservices.clients.models.request.ChangePasswordRequest;
import es.eduardopanero.microservices.clients.models.request.CreateClientRequest;
import es.eduardopanero.microservices.clients.models.request.ForgotPasswordRequest;
import es.eduardopanero.microservices.clients.models.request.LoginRequest;
import es.eduardopanero.microservices.clients.repositories.ClientsRepository;
import es.eduardopanero.microservices.clients.repositories.TokensRepository;
import es.eduardopanero.microservices.clients.services.commons.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;


@Service
public class ClientsService {

	@Autowired
	private ClientsRepository clientsRepository;

	@Autowired
	private TokensRepository tokensRepository;

	@Autowired
	private JwtService jwtService;

	public Mono<Client> createClient(CreateClientRequest request) {
		return request.validateAndMap()
				.flatMap(client -> this.clientsRepository
						.findByEmail(client.getEmail())
						.flatMap(__ -> Mono.error(new ValidationException("the email is already registered")))
						.switchIfEmpty(Mono.defer(() -> {
							byte[] salt = GeneratorService.generateSalt();
							client.setSalt(salt);
							client.setPassword(getHashedPassword(request.getPassword(), salt));
							return this.clientsRepository.save(client)
									.flatMap(x -> this.createToken(x.getClientId(),
											GeneratorService.generateRandomString(),
											TokenType.validation)
											.map(__ -> x)
									);
						}))
						.cast(Client.class));
	}

	public Mono<Client> getClient(UUID clientId) {
		return this.clientsRepository.findById(clientId);
	}

	public Mono<String> login(LoginRequest request) {
		return request.validate()
				.flatMap(login -> this.clientsRepository
						.findByEmail(login.getEmail())
						.flatMap(client -> {
							byte[] passwordHashed = getHashedPassword(login.getPassword(), client.getSalt());
							if (Arrays.equals(passwordHashed, client.getPassword()) && client.isEnabled()) {
								return jwtService.createJwt(client.getClientId());
							} else {
								return Mono.error(new ValidationException("the email or password are incorrect or is not enabled"));
							}
						})
						.switchIfEmpty(Mono.error(new ValidationException("the email or password are incorrect or is not enabled"))));
	}

	public Mono<Void> activateClient(String token) {
		return this.tokensRepository.findByToken(token, TokenType.validation)
				.flatMap(t -> (LocalDate.now().compareTo(t.getExpirationDate()) > 0) ? Mono.empty() :
						this.clientsRepository.findById(t.getClientId())
								.flatMap(client -> this.clientsRepository.save(client.withEnabled(true)))
								.flatMap(__ -> this.tokensRepository.delete(t)))
				.then();
	}

	public Mono<Void> changePassword(ChangePasswordRequest request, UUID clientId) {
		return request.validate()
				.flatMap(changePassword -> this.clientsRepository.findById(clientId)
						.switchIfEmpty(Mono.error(new ValidationException("the clientId doesnt exist in database")))
						.flatMap(client -> this.clientsRepository
								.save(client
										.withPassword(getHashedPassword(changePassword.getPassword(), client.getSalt())))
						)).then();
	}

	public Mono<Void> changePassword(ChangePasswordRequest request, String token) {
		return request.validate()
				.flatMap(changePassword -> this.tokensRepository.findByToken(token, TokenType.forgotPassword)
						.flatMap(t -> this.changePassword(changePassword, t.getClientId())));
	}

	public Mono<Void> forgotPassword(ForgotPasswordRequest request) {
		return request.validate()
				.flatMap(forgotPassword -> this.clientsRepository.findByEmail(forgotPassword.getEmail())
						.flatMap(client -> this.createToken(client.getClientId(),
								GeneratorService.generateRandomString(),
								TokenType.forgotPassword))).then();
	}

	private Mono<Token> createToken(UUID clientId, String token, TokenType type) {
		return this.tokensRepository.save(new Token()
				.withClientId(clientId)
				.withTokenType(type)
				.withExpirationDate(LocalDate.now().plusDays(1))
				.withToken(token));
	}

	private byte[] getHashedPassword(String password, byte[] salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
			byte[] hash = new byte[passwordBytes.length + salt.length];
			return digest.digest(hash);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}

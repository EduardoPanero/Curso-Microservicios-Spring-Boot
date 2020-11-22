package es.eduardopanero.microservices.clients.config.security;

import es.eduardopanero.microservices.clients.services.ClientsService;
import es.eduardopanero.microservices.clients.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ClientsService clientsService;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();

		try {
			Jws<Claims> claimsJws = jwtService.validate(authToken);
			Claims claims = claimsJws.getBody();
			List<String> rolesMap = List.of("USER");
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (String rolemap : rolesMap) {
				authorities.add(new SimpleGrantedAuthority(rolemap));
			}

			return this.clientsService.getClient(UUID.fromString(claims.getSubject()))
					.map(x -> (Authentication) new UsernamePasswordAuthenticationToken(x.getClientId(), null, authorities))
					.switchIfEmpty(Mono.empty());

		} catch (Exception e) {
			return Mono.empty();
		}
	}
}

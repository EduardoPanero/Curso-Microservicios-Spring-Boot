package es.eduardopanero.microservices.clients.services;

import es.eduardopanero.microservices.clients.config.ClientsConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

	@Autowired
	private ClientsConfiguration clientsConfiguration;

	private Key getSecretKey(){
		try {
			return new SecretKeySpec(this.clientsConfiguration.getSecret().getBytes("UTF-8"), SignatureAlgorithm.HS256.getJcaName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Mono<String> createJwt(UUID clientId) {
		return Mono.just(Jwts.builder()
				.setIssuer("identity")
				.setSubject(clientId.toString())
				.setExpiration(Date.from(Instant.now().plus(Duration.ofHours(30))))
				.setIssuedAt(Date.from(Instant.now()))
				.signWith(this.getSecretKey(), SignatureAlgorithm.HS256)
				.compact());
	}

	public Jws<Claims> validate(String jwt) {
		return Jwts.parserBuilder()
				.setSigningKey(this.getSecretKey())
				.build()
				.parseClaimsJws(jwt);
	}
}
package es.eduardopanero.microservices.clients.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {


	public Mono<String> createJwt(UUID clientId) {
		return Mono.just(Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "secret")
				.setIssuer("identity")
				.setSubject(clientId.toString())
				.setExpiration(Date.from(Instant.now().plus(Duration.ofHours(30))))
				.setIssuedAt(Date.from(Instant.now()))
				.compact());
	}

	public Jws<Claims> validate(String jwt) {
		return Jwts.parser()
				.setSigningKey("secret")
				.parseClaimsJws(jwt);
	}
}

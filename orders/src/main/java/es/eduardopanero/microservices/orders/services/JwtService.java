package es.eduardopanero.microservices.orders.services;

import es.eduardopanero.microservices.orders.config.OrdersConfiguration;
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
	private OrdersConfiguration ordersConfiguration;

	private Key getSecretKey(){
		try {
			return new SecretKeySpec(this.ordersConfiguration.getSecret().getBytes("UTF-8"), SignatureAlgorithm.HS256.getJcaName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Jws<Claims> validate(String jwt) {
		return Jwts.parserBuilder()
				.setSigningKey(this.getSecretKey())
				.build()
				.parseClaimsJws(jwt);
	}
}

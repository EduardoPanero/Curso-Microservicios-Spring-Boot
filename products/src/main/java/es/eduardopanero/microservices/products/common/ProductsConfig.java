package es.eduardopanero.microservices.products.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductsConfig {

	@Value("${profile}")
	private String profile;

	public String getProfile() {
		return profile;
	}
}

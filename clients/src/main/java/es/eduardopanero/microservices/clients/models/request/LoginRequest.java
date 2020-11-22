package es.eduardopanero.microservices.clients.models.request;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Client;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginRequest {
	private static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Mono<LoginRequest> validate() {
		List<String> errors = new ArrayList();
		if(this.getEmail() == null || this.getEmail().trim().isEmpty()) {
			errors.add("the field email is required");
		}else if(!EMAIL_REGEX.matcher(this.getEmail()).find()){
			errors.add("the field email must be with a valid format");
		}

		if(this.getPassword() == null || this.getPassword().trim().isEmpty()) {
			errors.add("the field password is required");
		}

		if(errors.isEmpty()){
			return Mono.just(this);
		}else{
			return Mono.error(new ValidationException(errors));
		}
	}

}



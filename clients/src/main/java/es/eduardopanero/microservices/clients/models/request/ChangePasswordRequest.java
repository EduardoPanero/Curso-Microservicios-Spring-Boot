package es.eduardopanero.microservices.clients.models.request;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Client;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ChangePasswordRequest {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String passwordConfirmation;

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Mono<ChangePasswordRequest> validate() {
		List<String> errors = new ArrayList();
		if(this.getPassword() == null || this.getPassword().trim().isEmpty()) {
			errors.add("the field password is required");
		}

		if(this.getPasswordConfirmation() == null || this.getPasswordConfirmation().trim().isEmpty()) {
			errors.add("the field password confirmation is required");
		}else if(!this.getPassword().equals(this.getPasswordConfirmation())) {
			errors.add("the field password and password confirmation must be equal");
		}

		if(errors.isEmpty()){
			return Mono.just(this);
		}else{
			return Mono.error(new ValidationException(errors));
		}
	}
}

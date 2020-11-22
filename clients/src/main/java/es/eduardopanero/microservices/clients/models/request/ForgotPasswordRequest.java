package es.eduardopanero.microservices.clients.models.request;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ForgotPasswordRequest {
	private static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Mono<ForgotPasswordRequest> validate() {
		List<String> errors = new ArrayList();
		if(this.getEmail() == null || this.getEmail().trim().isEmpty()) {
			errors.add("the field email is required");
		}else if(!EMAIL_REGEX.matcher(this.getEmail()).find()){
			errors.add("the field email must be with a valid format");
		}

		if(errors.isEmpty()){
			return Mono.just(this);
		}else{
			return Mono.error(new ValidationException(errors));
		}
	}
}

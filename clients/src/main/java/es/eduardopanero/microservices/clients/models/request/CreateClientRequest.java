package es.eduardopanero.microservices.clients.models.request;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Client;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateClientRequest {
	private static final Pattern EMAIL_REGEX =
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

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

	private String passwordConfirmation;
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Client mapToClient() {
		return new Client()
				.withName(this.getName())
				.withEmail(this.getEmail());
	}

	public Mono<Client> validateAndMap() {
		List<String> errors = new ArrayList();
		if(this.getName() == null || this.getName().trim().isEmpty()) {
			errors.add("the field name is required");
		}

		if(this.getEmail() == null || this.getEmail().trim().isEmpty()) {
			errors.add("the field email is required");
		}else if(!EMAIL_REGEX.matcher(this.getEmail()).find()){
			errors.add("the field email must be with a valid format");
		}

		if(this.getPassword() == null || this.getPassword().trim().isEmpty()) {
			errors.add("the field password is required");
		}

		if(this.getPasswordConfirmation() == null || this.getPasswordConfirmation().trim().isEmpty()) {
			errors.add("the field password confirmation is required");
		}else if(!this.getPassword().equals(this.getPasswordConfirmation())) {
			errors.add("the field password and password confirmation must be equal");
		}

		if(errors.isEmpty()){
			return Mono.just(this.mapToClient());
		}else{
			return Mono.error(new ValidationException(errors));
		}
	}

}



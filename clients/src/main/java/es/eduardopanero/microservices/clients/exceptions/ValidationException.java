package es.eduardopanero.microservices.clients.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {
	private List<String> validations;

	public ValidationException(List<String> validations) {
		this.validations = validations;
	}

	public ValidationException(String validation) {
		this.validations = new ArrayList<String>();
		this.validations.add(validation);
	}

	public List<String> getValidations() {
		return validations;
	}
}

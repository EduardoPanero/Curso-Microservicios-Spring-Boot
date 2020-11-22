package es.eduardopanero.microservices.clients.models.enums;

public enum TokenType {
	validation,
	forgotPassword;

	public static TokenType fromValue(String value) {
		for (TokenType tokenType : values())
			if (tokenType.name().equalsIgnoreCase(value)) {
				return tokenType;
			}
		return null;
	}
}

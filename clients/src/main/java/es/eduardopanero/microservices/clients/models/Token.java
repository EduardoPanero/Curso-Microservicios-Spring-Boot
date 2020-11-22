package es.eduardopanero.microservices.clients.models;

import es.eduardopanero.microservices.clients.models.enums.TokenType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Table("tokens")
public class Token {

	@Id
	private Long tokenId;

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	private UUID clientId;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	private TokenType tokenType;

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	private LocalDate expirationDate;

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Token withClientId(UUID clientId){
		this.setClientId(clientId);
		return this;
	}

	public Token withTokenType(TokenType tokenType){
		this.setTokenType(tokenType);
		return this;
	}

	public Token withExpirationDate(LocalDate expirationDate){
		this.setExpirationDate(expirationDate);
		return this;
	}

	public Token withToken(String token){
		this.setToken(token);
		return this;
	}
}

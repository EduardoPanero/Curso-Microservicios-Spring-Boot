package es.eduardopanero.microservices.clients.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("clients")
public class Client  {
	@Id
	private UUID clientId;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

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

	private byte[] password;

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	private byte[] salt;

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Client withClientId(UUID clientId){
		this.setClientId(clientId);
		return this;
	}

	public Client withName(String name){
		this.setName(name);
		return this;
	}

	public Client withEmail(String email){
		this.setEmail(email);
		return this;
	}

	public Client withPassword(byte[] password){
		this.setPassword(password);
		return this;
	}

	public Client withSalt(byte[] salt){
		this.setSalt(salt);
		return this;
	}

	public Client withEnabled(boolean enabled){
		this.setEnabled(enabled);
		return this;
	}

}

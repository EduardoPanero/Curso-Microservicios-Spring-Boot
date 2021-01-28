package es.eduardopanero.microservices.orders.models;

import reactor.core.publisher.Mono;

import java.util.UUID;

public class Client {

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

	public Client withEnabled(boolean enabled){
		this.setEnabled(enabled);
		return this;
	}
}

package es.eduardopanero.microservices.clients.models.response;

import es.eduardopanero.microservices.clients.models.Client;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ClientResponse {

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

	public ClientResponse withClientId(UUID clientId){
		this.setClientId(clientId);
		return this;
	}

	public ClientResponse withName(String name){
		this.setName(name);
		return this;
	}

	public ClientResponse withEmail(String email){
		this.setEmail(email);
		return this;
	}

	public ClientResponse withEnabled(boolean enabled){
		this.setEnabled(enabled);
		return this;
	}

	public static Mono<ClientResponse> mapToClientResponse(Client client) {
		return Mono.just(new ClientResponse()
				.withClientId(client.getClientId())
				.withName(client.getName())
				.withEmail(client.getEmail())
				.withEnabled(client.isEnabled()));

	}

}

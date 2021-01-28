package es.eduardopanero.microservices.orders.services;


import es.eduardopanero.microservices.orders.config.OrdersConfiguration;
import es.eduardopanero.microservices.orders.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ClientsService {

	@Autowired
	private OrdersConfiguration ordersConfiguration;

	public Mono<Client> getClient(String token) {
		return WebClient
				.create(String.format("%s/me", ordersConfiguration.getClientsServiceUrl()))
				.get()
				.header("Authorization", String.format("Bearer %s", token))
				.retrieve()
				.bodyToMono(Client.class);
	}

	public Mono<Client> getClientById(UUID clientId, String token) {
		return WebClient
				.create(String.format("%s/clients/%s", ordersConfiguration.getClientsServiceUrl(), clientId.toString()))
				.get()
				.header("Authorization", String.format("Bearer %s", token))
				.retrieve()
				.bodyToMono(Client.class);
	}
}

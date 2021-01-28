package es.eduardopanero.microservices.orders.services;


import es.eduardopanero.microservices.orders.config.OrdersConfiguration;
import es.eduardopanero.microservices.orders.models.Address;
import es.eduardopanero.microservices.orders.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AddressesService {

	@Autowired
	private OrdersConfiguration ordersConfiguration;

	public Mono<Address> getDefaultAddress(String token) {
		return WebClient
				.create(String.format("%s/addresses/default", ordersConfiguration.getClientsServiceUrl()))
				.get()
				.header("Authorization", String.format("Bearer %s", token))
				.retrieve()
				.bodyToMono(Address.class);
	}

	public Mono<Address> getAddressById(Long addressId, String token) {
		return WebClient
				.create(String.format("%s/address/%d", ordersConfiguration.getClientsServiceUrl(), addressId))
				.get()
				.header("Authorization", String.format("Bearer %s", token))
				.retrieve()
				.bodyToMono(Address.class);
	}
}

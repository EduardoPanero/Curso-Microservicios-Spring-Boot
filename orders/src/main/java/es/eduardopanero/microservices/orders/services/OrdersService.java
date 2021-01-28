package es.eduardopanero.microservices.orders.services;


import es.eduardopanero.microservices.orders.config.OrdersConfiguration;
import es.eduardopanero.microservices.orders.models.Address;
import es.eduardopanero.microservices.orders.models.Client;
import es.eduardopanero.microservices.orders.models.Order;
import es.eduardopanero.microservices.orders.models.request.CreateOrderRequest;
import es.eduardopanero.microservices.orders.models.response.OrderResponse;
import es.eduardopanero.microservices.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class OrdersService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientsService clientsService;

	@Autowired
	private AddressesService addressesService;

	@Autowired
	private OrdersConfiguration ordersConfiguration;

	public Mono<OrderResponse> createOrder(CreateOrderRequest request, String token) {
		Mono<Client> client = this.clientsService.getClient(token);
		Mono<Address> address = this.addressesService.getDefaultAddress(token);
		return Mono.zip(client, address).flatMap(data ->
			 this.orderRepository.save(new Order()
					.withClientId(data.getT1().getClientId())
					.withAddressId(data.getT2().getAddressId())
					.withOrderDate(new Date())
					.withAmount(request.getAmount()))
		).flatMap(order -> mapToOrderResponse(order, token));
	}

	private Mono<OrderResponse> mapToOrderResponse(Order order, String token) {
		Mono<Client> client = this.clientsService.getClientById(order.getClientId(), token);
		Mono<Address> address = this.addressesService.getAddressById(order.getAddressId(), token);

		return Mono.zip(client, address).map(data -> new OrderResponse()
				.withOrderId(order.getOrderId())
				.withClient(data.getT1())
				.withAddress(data.getT2())
				.withAmount(order.getAmount())
				.withOrderDate(order.getOrderDate()));
	}

}

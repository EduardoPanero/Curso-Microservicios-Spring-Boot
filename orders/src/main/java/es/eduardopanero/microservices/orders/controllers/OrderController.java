package es.eduardopanero.microservices.orders.controllers;

import es.eduardopanero.microservices.orders.models.Order;
import es.eduardopanero.microservices.orders.models.request.CreateOrderRequest;
import es.eduardopanero.microservices.orders.services.ClientsService;
import es.eduardopanero.microservices.orders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
public class OrderController {

	@Autowired
	private OrdersService ordersService;

	@PostMapping("")
	public ResponseEntity<Mono> index(@RequestBody CreateOrderRequest request, ServerHttpRequest serverHttpRequest) {
		String token = serverHttpRequest.getHeaders()
				.getFirst("Authorization")
				.replace("Bearer ", "");

		return ResponseEntity.ok(this.ordersService
				.createOrder(request, token));
	}
}

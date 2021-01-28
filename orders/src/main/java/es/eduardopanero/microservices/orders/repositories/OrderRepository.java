package es.eduardopanero.microservices.orders.repositories;

import es.eduardopanero.microservices.orders.models.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {

}

package es.eduardopanero.microservices.clients.repositories;

import es.eduardopanero.microservices.clients.models.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClientsRepository extends ReactiveCrudRepository<Client, UUID> {
	@Query("SELECT * FROM clients WHERE email = :email LIMIT 1")
	Mono<Client> findByEmail(String email);
}

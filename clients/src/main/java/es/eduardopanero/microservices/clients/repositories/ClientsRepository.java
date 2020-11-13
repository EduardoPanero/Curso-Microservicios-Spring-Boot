package es.eduardopanero.microservices.clients.repositories;

import es.eduardopanero.microservices.clients.models.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ClientsRepository extends ReactiveCrudRepository<Client, UUID> {
}

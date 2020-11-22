package es.eduardopanero.microservices.clients.repositories;

import es.eduardopanero.microservices.clients.models.Address;
import es.eduardopanero.microservices.clients.models.Client;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AddressesRepository extends ReactiveCrudRepository<Address, Long> {
	@Query("SELECT * FROM Addresses WHERE client_id = :clientId AND default_address IS TRUE LIMIT 1")
	Flux<Address> findDefaultAddress(UUID clientId);

	@Query("SELECT * FROM Addresses WHERE client_id = :clientId")
	Flux<Address> findAddressesByClientId(UUID clientId);
}

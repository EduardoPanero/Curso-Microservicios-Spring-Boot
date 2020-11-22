package es.eduardopanero.microservices.clients.services;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Address;
import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.models.request.CreateAddressRequest;
import es.eduardopanero.microservices.clients.repositories.AddressesRepository;
import es.eduardopanero.microservices.clients.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.XMLFormatter;

@Service
public class AddressesService {

	@Autowired
	private AddressesRepository addressesRepository;
	@Autowired
	private ClientsRepository clientRepository;

	public Mono<Address> createAddress(CreateAddressRequest request, UUID clientId) {
		return request.validateAndMap(clientId)
				.flatMap(address -> this.addressesRepository
						.findDefaultAddress(clientId)
						.singleOrEmpty()
						.map(__ ->address.withDefaultAddress(false))
						.switchIfEmpty(Mono.just(address.withDefaultAddress(true))))
				.flatMap(this.addressesRepository::save);
	}

	public Flux<Address> getAddressesByClientId(UUID clientId) {
		 return this.clientRepository.findById(clientId)
				.flatMapMany(x -> this.addressesRepository.findAddressesByClientId(clientId))
				.switchIfEmpty(Flux.error(new ValidationException("this clientId doesn't exists")));
	}
}

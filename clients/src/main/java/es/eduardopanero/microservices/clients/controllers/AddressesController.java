package es.eduardopanero.microservices.clients.controllers;

import es.eduardopanero.microservices.clients.models.Address;
import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.models.request.CreateAddressRequest;
import es.eduardopanero.microservices.clients.models.request.CreateClientRequest;
import es.eduardopanero.microservices.clients.services.AddressesService;
import es.eduardopanero.microservices.clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
public class AddressesController {
	@Autowired
	private AddressesService addressesService;
	@Autowired
	private ClientsService clientsService;

	@PostMapping("address")
	public ResponseEntity<Mono<Address>> createAddress(@RequestBody CreateAddressRequest request, Principal principal) {
		return ResponseEntity.ok(this.addressesService
				.createAddress(request, UUID.fromString(principal.getName())));
	}

	@GetMapping("addresses")
	public ResponseEntity<Flux<Address>> getAddresses(Principal principal) {
		return ResponseEntity.ok(this.addressesService
				.getAddressesByClientId(UUID.fromString(principal.getName())));
	}

	@GetMapping("addresses/default")
	public ResponseEntity<Mono<Address>> getDefaultAddress(Principal principal) {
		return ResponseEntity.ok(this.addressesService
				.getDefaultAddressByClientId(UUID.fromString(principal.getName())));
	}

	@GetMapping("address/{addressId}")
	public ResponseEntity<Mono<Address>> getAddressById(@PathVariable(value="addressId") Long addressId) {
		return ResponseEntity.ok(this.addressesService
				.getAddressById(addressId));
	}

}
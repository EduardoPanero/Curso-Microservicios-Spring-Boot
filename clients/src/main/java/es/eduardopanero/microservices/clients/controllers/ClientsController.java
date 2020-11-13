package es.eduardopanero.microservices.clients.controllers;

import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("clients")
public class ClientsController {
	@Autowired
	private ClientsRepository clientsRepository;

	@GetMapping("")
	public Flux<Client> getClients() {
		return clientsRepository.findAll();
	}
}

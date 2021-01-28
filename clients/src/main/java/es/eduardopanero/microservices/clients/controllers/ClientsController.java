package es.eduardopanero.microservices.clients.controllers;

import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.models.request.ChangePasswordRequest;
import es.eduardopanero.microservices.clients.models.request.CreateClientRequest;
import es.eduardopanero.microservices.clients.models.request.ForgotPasswordRequest;
import es.eduardopanero.microservices.clients.models.request.LoginRequest;
import es.eduardopanero.microservices.clients.models.response.ClientResponse;
import es.eduardopanero.microservices.clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;


@RestController
public class ClientsController {
	@Autowired
	private ClientsService clientsService;

	@GetMapping("clients/{clientId}")
	public ResponseEntity<Mono<ClientResponse>> getClientById(@PathVariable(value="clientId") UUID clientId) {
		return ResponseEntity.ok(this.clientsService
				.getClient(clientId)
				.flatMap(ClientResponse::mapToClientResponse));
	}

	@GetMapping("me")
	public ResponseEntity<Mono<ClientResponse>> getMe(Principal principal) {
		return ResponseEntity.ok(this.clientsService
				.getClient(UUID.fromString(principal.getName()))
				.flatMap(ClientResponse::mapToClientResponse));
	}

	@PostMapping("sign-up")
	public ResponseEntity<Mono<ClientResponse>> createClient(@RequestBody CreateClientRequest request) {
		return ResponseEntity.ok(this.clientsService
				.createClient(request)
				.flatMap(ClientResponse::mapToClientResponse));
	}

	@PostMapping("activate/{token}")
	public ResponseEntity<Mono<Void>> activateClient(@PathVariable("token") String token) {
		return ResponseEntity.ok(this.clientsService.activateClient(token));
	}

	@PostMapping("change-password")
	public ResponseEntity<Mono<Void>> changePassword(@RequestBody ChangePasswordRequest request, Principal principal) {
		return ResponseEntity.ok(this.clientsService.changePassword(request, UUID.fromString(principal.getName())));
	}

	@PostMapping("change-password/{token}")
	public ResponseEntity<Mono<Void>> changePassword(@PathVariable("token") String token, @RequestBody ChangePasswordRequest request) {
		return ResponseEntity.ok(this.clientsService.changePassword(request, token));
	}

	@PostMapping("forgot-password")
	public ResponseEntity<Mono<Void>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
		return ResponseEntity.ok(this.clientsService.forgotPassword(request));
	}

	@PostMapping("login")
	public ResponseEntity<Mono<String>> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(this.clientsService
				.login(request));
	}

}

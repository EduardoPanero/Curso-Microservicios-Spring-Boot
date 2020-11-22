package es.eduardopanero.microservices.clients.repositories;

import es.eduardopanero.microservices.clients.models.Address;
import es.eduardopanero.microservices.clients.models.Client;
import es.eduardopanero.microservices.clients.models.Token;
import es.eduardopanero.microservices.clients.models.enums.TokenType;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TokensRepository extends ReactiveCrudRepository<Token, Long> {
	@Query("SELECT * FROM Tokens WHERE token = :token AND token_type = :tokenType LIMIT 1")
	Mono<Token> findByToken(String token, TokenType tokenType);
}

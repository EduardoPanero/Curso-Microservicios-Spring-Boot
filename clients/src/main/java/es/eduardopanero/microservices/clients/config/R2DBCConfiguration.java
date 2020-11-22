package es.eduardopanero.microservices.clients.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;

import java.util.Optional;

@Configuration
@EnableR2dbcRepositories
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {
	@Autowired
	public ClientsConfiguration clientsConfiguration;

	@Bean
	public PostgresqlConnectionFactory connectionFactory() {
		return new PostgresqlConnectionFactory(
				PostgresqlConnectionConfiguration.builder()
						.host(clientsConfiguration.getHost())
						.port(clientsConfiguration.getPort())
						.username(clientsConfiguration.getUsername())
						.password(clientsConfiguration.getPassword())
						.database(clientsConfiguration.getDatabase())
						.build()
		);
	}

	@Override
	@Bean
	public R2dbcMappingContext r2dbcMappingContext(Optional<NamingStrategy> namingStrategy, R2dbcCustomConversions r2dbcCustomConversions) {
		R2dbcMappingContext context = new R2dbcMappingContext(namingStrategy.orElse(NamingStrategy.INSTANCE));
		context.setSimpleTypeHolder(r2dbcCustomConversions.getSimpleTypeHolder());
		return context;
	}
}

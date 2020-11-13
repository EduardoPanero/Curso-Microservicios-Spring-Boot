package es.eduardopanero.microservices.clients.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

	@Autowired
	private ClientsConfiguration clientsConfiguration;

	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		return new Flyway(Flyway.configure()
				.baselineOnMigrate(true)
				.dataSource(
						this.clientsConfiguration.getUrl(),
						this.clientsConfiguration.getUsername(),
						this.clientsConfiguration.getPassword()));
	}
}

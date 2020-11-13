package es.eduardopanero.microservices.clients.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientsConfiguration {

	@Value("${spring.data.postgres.host}")
	private String host;

	public String getHost() {
		return host;
	}

	@Value("${spring.data.postgres.port}")
	private int port;

	public int getPort() {
		return port;
	}

	@Value("${spring.data.postgres.database}")
	private String database;

	public String getDatabase() {
		return database;
	}


	public String getUrl() {
		return String.format("jdbc:postgresql://%s:%d/%s", this.getHost(), this.getPort(), this.getDatabase());
	}

	@Value("${spring.data.postgres.username}")
	private String username;

	public String getUsername() {
		return username;
	}

	@Value("${spring.data.postgres.password}")
	private String password;

	public String getPassword() {
		return password;
	}
}

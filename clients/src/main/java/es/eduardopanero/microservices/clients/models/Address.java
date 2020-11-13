package es.eduardopanero.microservices.clients.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("addresses")
public class Address {
	@Id
	private UUID clientId;
	@Id
	private Long id;

	@NonNull
	private String street;

	@NonNull
	private Long number;

	@NonNull
	private String zipCode;

	private Long flat;

	private String door;
}

package es.eduardopanero.microservices.clients.models.request;

import es.eduardopanero.microservices.clients.exceptions.ValidationException;
import es.eduardopanero.microservices.clients.models.Address;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class CreateAddressRequest {
	private static final Pattern ZIPCODE_REGEX =
			Pattern.compile("^[0-9]{5,6}$", Pattern.CASE_INSENSITIVE);

	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	private Long number;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	private String zipCode;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	private Long flat;

	public Long getFlat() {
		return flat;
	}

	public void setFlat(Long flat) {
		this.flat = flat;
	}

	private String door;

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public Address mapToAddress(UUID clientId) {
		return new Address()
				.withClientId(clientId)
				.withStreet(this.getStreet())
				.withNumber(this.getNumber())
				.withZipCode(this.getZipCode())
				.withFlat(this.getFlat())
				.withDoor(this.getDoor());
	}

	public Mono<Address> validateAndMap(UUID clientId) {
		List<String> errors = new ArrayList();
		if(this.getStreet() == null || this.getStreet().trim().isEmpty()) {
			errors.add("the field street is required");
		}

		if(this.getNumber() == null || this.getNumber() <= 0) {
			errors.add("the field number is required");
		}

		if(this.getZipCode() == null || this.getZipCode().trim().isEmpty()) {
			errors.add("the field password is required");
		}else if(!ZIPCODE_REGEX.matcher(this.getZipCode()).find()){
			errors.add("the field zipcode must be with a valid format");
		}

		if(errors.isEmpty()){
			return Mono.just(this.mapToAddress(clientId));
		}else{
			return Mono.error(new ValidationException(errors));
		}
	}

}



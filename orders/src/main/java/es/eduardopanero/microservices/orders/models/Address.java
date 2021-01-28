package es.eduardopanero.microservices.orders.models;

import java.util.UUID;

public class Address {

	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	private UUID clientId;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

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

	private boolean defaultAddress;

	public boolean isDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
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

}

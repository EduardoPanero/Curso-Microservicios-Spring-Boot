package es.eduardopanero.microservices.clients.models.response;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

public class AddressResponse {

	@Id
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public AddressResponse withAddressId(Long addressId){
		this.setAddressId(addressId);
		return this;
	}

	public AddressResponse withStreet(String street){
		this.setStreet(street);
		return this;
	}

	public AddressResponse withNumber(Long number){
		this.setNumber(number);
		return this;
	}

	public AddressResponse withZipCode(String zipCode){
		this.setZipCode(zipCode);
		return this;
	}

	public AddressResponse withDefaultAddress(boolean defaultAddress) {
		this.setDefaultAddress(defaultAddress);
		return this;
	}

	public AddressResponse withFlat(Long flat){
		this.setFlat(flat);
		return this;
	}

	public AddressResponse withDoor(String door){
		this.setDoor(door);
		return this;
	}
}

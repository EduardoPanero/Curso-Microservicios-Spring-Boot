package es.eduardopanero.microservices.orders.models.response;

import es.eduardopanero.microservices.orders.models.Address;
import es.eduardopanero.microservices.orders.models.Client;
import es.eduardopanero.microservices.orders.models.Order;

import java.util.Date;
import java.util.UUID;

public class OrderResponse {

	private UUID orderId;

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private Date orderDate;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public OrderResponse withOrderId(UUID orderId) {
		this.setOrderId(orderId);
		return this;
	}

	public OrderResponse withClient(Client client) {
		this.setClient(client);
		return this;
	}

	public OrderResponse withAddress(Address address){
		this.setAddress(address);
		return this;
	}

	public OrderResponse withOrderDate(Date orderDate) {
		this.setOrderDate(orderDate);
		return this;
	}

	public OrderResponse withAmount(Double amount) {
		this.setAmount(amount);
		return this;
	}

}

package es.eduardopanero.microservices.orders.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@Table("orders")
public class Order {

	@Id
	private UUID orderId;

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	private UUID clientId;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public Order withOrderId(UUID orderId) {
		this.setOrderId(orderId);
		return this;
	}

	public Order withClientId(UUID clientId) {
		this.setClientId(clientId);
		return this;
	}

	public Order withAddressId(Long addressId){
		this.setAddressId(addressId);
		return this;
	}

	public Order withOrderDate(Date orderDate) {
		this.setOrderDate(orderDate);
		return this;
	}

	public Order withAmount(Double amount) {
		this.setAmount(amount);
		return this;
	}
}

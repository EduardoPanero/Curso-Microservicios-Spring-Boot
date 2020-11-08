package es.eduardopanero.microservices.products.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	private Double price;

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Product withId(Long id) {
		this.setId(id);
		return this;
	}

	public Product withName(String name) {
		this.setName(name);
		return this;
	}

	public Product withPrice(Double price) {
		this.setPrice(price);
		return this;
	}
}

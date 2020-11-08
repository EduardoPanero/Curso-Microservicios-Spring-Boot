package es.eduardopanero.microservices.products.services;

import es.eduardopanero.microservices.products.models.Product;
import es.eduardopanero.microservices.products.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	public List<Product> get() {
		List<Product> products = new ArrayList<Product>();
		this.productsRepository.findAll().forEach(products::add);
		return products;
	}

	public Optional<Product> get(Long id) {
		return this.productsRepository.findById(id);
	}

	public Product create(Product product) {
		return this.productsRepository.save(product);
	}

	public Optional<Product> update(Long id, Double price) {
		return this.productsRepository.findById(id)
				.map(product -> Optional.of(this.productsRepository.save(product.withPrice(price))))
				.orElseGet(() -> Optional.empty());
	}

	public void delete(Long id) {
		this.productsRepository.deleteById(id);
	}
}

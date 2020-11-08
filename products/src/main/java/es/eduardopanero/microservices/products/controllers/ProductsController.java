package es.eduardopanero.microservices.products.controllers;

import es.eduardopanero.microservices.products.models.Product;
import es.eduardopanero.microservices.products.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController(value = "")
public class ProductsController {
	@Autowired
	private ProductsService productsService;

	@GetMapping
	public ResponseEntity<List<Product>> get() {
		try {
			return ResponseEntity.ok(this.productsService.get());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> get(@PathVariable("id") Long id) {
		try {
			return this.productsService.get(id)
					.map(product -> ResponseEntity.ok(product))
					.orElseGet(()-> ResponseEntity.notFound().build());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("")
	public ResponseEntity<Product> create(@RequestBody Product product) {
		try {
			Product result =  this.productsService.create(product);
			return ResponseEntity.created(URI.create("/"+product.getId())).body(result);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Double price) {
		try {
			return this.productsService.get(id)
					.map(product -> ResponseEntity.ok(product))
					.orElseGet(()-> ResponseEntity.notFound().build());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			this.productsService.delete(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

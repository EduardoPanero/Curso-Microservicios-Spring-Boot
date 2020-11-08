package es.eduardopanero.microservices.products.repositories;

import es.eduardopanero.microservices.products.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
}

package pl.warehouseapi.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warehouseapi.domain.agregate.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

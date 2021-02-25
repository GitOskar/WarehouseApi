package pl.warehouseapi.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warehouseapi.domain.agregate.OrderPart;

@Repository
public interface OrderPartRepository extends JpaRepository<OrderPart, Integer> {
}

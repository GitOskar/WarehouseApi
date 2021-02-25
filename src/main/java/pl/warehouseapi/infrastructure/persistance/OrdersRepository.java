package pl.warehouseapi.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warehouseapi.domain.agregate.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
}

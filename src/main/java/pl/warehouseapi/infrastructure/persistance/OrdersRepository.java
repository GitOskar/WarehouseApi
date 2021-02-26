package pl.warehouseapi.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warehouseapi.domain.agregate.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderDateBetween(LocalDateTime from, LocalDateTime to);
}

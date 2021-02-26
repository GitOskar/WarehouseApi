package pl.warehouseapi.domain.agregate;

import lombok.*;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;
    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderPart> orderParts;

    public Order(LocalDateTime orderDate, BigDecimal totalPrice) {
        this(orderDate, totalPrice, null);
    }

    public void addPart(OrderPart part) {
        orderParts.add(part);
    }
}

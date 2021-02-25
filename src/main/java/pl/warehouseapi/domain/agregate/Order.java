package pl.warehouseapi.domain.agregate;

import lombok.*;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
    @OneToMany(mappedBy = "order")
    Set<OrderPart> orderParts;
}

package pl.warehouseapi.domain.agregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "ORDER_PART")
public class OrderPart extends BaseEntity {
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public BigDecimal getPriceOfProduct() {
        return product.getPrice();
    }
}

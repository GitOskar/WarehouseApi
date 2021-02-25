package pl.warehouseapi.domain.agregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "ORDER_PART")
public class OrderPart extends BaseEntity {

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    Product product;

    public double getPriceOfProduct() {
        return product.getPrice();
    }
}

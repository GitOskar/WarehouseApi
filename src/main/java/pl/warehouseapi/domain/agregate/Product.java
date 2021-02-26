package pl.warehouseapi.domain.agregate;

import lombok.*;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "PRODUCT")
public class Product extends BaseEntity {
    @Column(name = "NAME")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Column(name = "PRICE")
    @DecimalMin(value = "0.01", message = "The price cannot be lower than 0.01")
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private List<OrderPart> orderParts;

    public Product(String name, BigDecimal price) {
        this(name, price, null);
    }
}

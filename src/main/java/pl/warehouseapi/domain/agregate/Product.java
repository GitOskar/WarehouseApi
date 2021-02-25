package pl.warehouseapi.domain.agregate;

import lombok.*;
import pl.warehouseapi.domain.primitive.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "PRODUCT")
public class Product extends BaseEntity {
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private double price;
    @OneToMany(mappedBy = "product")
    Set<OrderPart> orderParts;
}

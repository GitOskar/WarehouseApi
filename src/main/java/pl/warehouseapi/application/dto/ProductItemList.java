package pl.warehouseapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class ProductItemList {
    public int productId;
    @DecimalMin("1")
    public BigDecimal quantity;
}

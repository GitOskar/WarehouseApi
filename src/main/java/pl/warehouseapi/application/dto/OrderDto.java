package pl.warehouseapi.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private String orderDate;
    private BigDecimal totalPrice;

    public OrderDto(String orderDate, double totalPrice) {
        this.orderDate = orderDate;
        this.totalPrice = BigDecimal.valueOf(totalPrice);
    }
}

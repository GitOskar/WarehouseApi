package pl.warehouseapi.application.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Integer id;
    private String orderDate;
    private Double totalPrice;

    public OrderDto(String orderDate, Double totalPrice) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}

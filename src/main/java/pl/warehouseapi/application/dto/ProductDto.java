package pl.warehouseapi.application.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private Double price;
}

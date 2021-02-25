package pl.warehouseapi.presentation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.ProductItemList;
import pl.warehouseapi.application.service.OrderService;
import pl.warehouseapi.domain.agregate.Order;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    @Autowired
    OrderService service;

    public OrderDto makeAnOrder(List<ProductItemList> productList) {
        return service.makeAnOrder(productList);
    }
}

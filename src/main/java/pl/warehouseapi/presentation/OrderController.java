package pl.warehouseapi.presentation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.PeriodOfTime;
import pl.warehouseapi.application.dto.ProductItemList;
import pl.warehouseapi.application.service.OrderService;
import pl.warehouseapi.application.validation.MinQuantity;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehouseapi/order")
@AllArgsConstructor
@Validated
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping
    public OrderDto makeAnOrder(
            @NotEmpty(message = "Product list cannot be empty")
            @MinQuantity
            @RequestBody
            List<@Valid ProductItemList> productList) {
                return service.makeAnOrder(productList);
    }

    @GetMapping("/recalculate_order")
    public OrderDto recalculateOrder(@RequestBody int id) {
        return service.recalculateOrder(id);
    }

    @GetMapping("/orders_in_period_of_time")
    public List<OrderDto> ordersInPeriodOfTime(@RequestBody PeriodOfTime periodOfTime) {
        return service.ordersInPeriodOfTime(periodOfTime);
    }
}

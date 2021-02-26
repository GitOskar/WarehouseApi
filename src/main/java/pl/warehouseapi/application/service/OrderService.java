package pl.warehouseapi.application.service;

import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.PeriodOfTime;
import pl.warehouseapi.application.dto.ProductItemList;
import pl.warehouseapi.domain.agregate.Order;

import java.util.List;

public interface OrderService {
    OrderDto makeAnOrder(List<ProductItemList> productList);
    void calculateTotalPrice(Order order);
    OrderDto recalculateOrder(int id);
    List<OrderDto> ordersInPeriodOfTime(PeriodOfTime periodOfTime);
}

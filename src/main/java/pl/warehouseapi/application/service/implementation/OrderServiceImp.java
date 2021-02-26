package pl.warehouseapi.application.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.PeriodOfTime;
import pl.warehouseapi.application.dto.ProductItemList;
import pl.warehouseapi.application.dto.mapper.WarehouseApiMapper;
import pl.warehouseapi.application.service.OrderService;
import pl.warehouseapi.domain.agregate.Order;
import pl.warehouseapi.domain.agregate.OrderPart;
import pl.warehouseapi.domain.exception.OrderNotFoundException;
import pl.warehouseapi.domain.exception.ProductNotFoundException;
import pl.warehouseapi.infrastructure.persistance.OrderPartRepository;
import pl.warehouseapi.infrastructure.persistance.OrdersRepository;
import pl.warehouseapi.infrastructure.persistance.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderPartRepository orderPartRepository;
    @Autowired
    private WarehouseApiMapper mapper;

    @Override
    public OrderDto makeAnOrder(List<ProductItemList> productList) {
        Order order = new Order(LocalDateTime.now(), BigDecimal.ZERO, new ArrayList<>());

        productList.stream()
                .forEach(value -> order.addPart(
                        new OrderPart(
                                value.quantity,
                                order,
                                productRepository.findById(value.productId)
                                        .orElseThrow(() -> new ProductNotFoundException(value.productId)))));

        calculateTotalPrice(order);
        return mapper.OrderToOrderDto(ordersRepository.save(order));
    }

    @Override
    public OrderDto recalculateOrder(int id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        calculateTotalPrice(order);
        return mapper.OrderToOrderDto(order);
    }

    @Override @Transactional
    public void calculateTotalPrice(Order order) {
        BigDecimal totalPrice = order.getOrderParts().stream()
                .map(o -> o.getPriceOfProduct().multiply(o.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);
    }

    @Override
    public List<OrderDto> ordersInPeriodOfTime(PeriodOfTime periodOfTime) {
        return ordersRepository.findAllByOrderDateBetween(periodOfTime.getFrom(), periodOfTime.getTo()).stream()
                .map(mapper::OrderToOrderDto)
                .collect(Collectors.toList());
    }
}

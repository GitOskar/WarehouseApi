package pl.warehouseapi.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.ProductItemList;
import pl.warehouseapi.application.dto.mapper.WarehouseApiMapper;
import pl.warehouseapi.domain.agregate.Order;
import pl.warehouseapi.domain.agregate.OrderPart;
import pl.warehouseapi.infrastructure.persistance.OrderPartRepository;
import pl.warehouseapi.infrastructure.persistance.OrdersRepository;
import pl.warehouseapi.infrastructure.persistance.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

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
    @Transactional
    public OrderDto makeAnOrder(List<ProductItemList> productList) {
        Order order = ordersRepository.save(new Order());
        order.setOrderDate(LocalDateTime.now());

        productList.stream()
                .forEach(value -> orderPartRepository.save(
                        new OrderPart(
                                value.quantity,
                                order,
                                productRepository.findById(value.productId).orElseThrow())));

        calculateTotalPrice(order);
        return mapper.OrderToOrderDto(order);
    }

    @Override @Transactional
    public void calculateTotalPrice(Order order) {
        double totalPrice = order.getOrderParts().stream()
                .mapToDouble(o -> o.getQuantity() * o.getPriceOfProduct())
                .sum();

        order.setTotalPrice(totalPrice);
    }


}

package pl.warehouseapi.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.warehouseapi.application.service.OrderService;
import pl.warehouseapi.domain.agregate.Order;
import pl.warehouseapi.domain.agregate.OrderPart;
import pl.warehouseapi.domain.agregate.Product;
import pl.warehouseapi.infrastructure.persistance.OrderPartRepository;
import pl.warehouseapi.infrastructure.persistance.OrdersRepository;
import pl.warehouseapi.infrastructure.persistance.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.List;

@SpringBootTest
public class CalculateTotalPriceTest {
    @MockBean
    OrdersRepository ordersRepository;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    OrderPartRepository orderPartRepository;
    @Autowired
    OrderService service;

    @ParameterizedTest
    @MethodSource("provideParameters")
    void assert_that_calculation_returns_good_result(List<Product> products, List<BigDecimal> quantity, BigDecimal totalPrice) {
        Order order = new Order();
        order.setOrderParts(new ArrayList<>());

        for (int i=0 ; i<products.size() ; i++) {
            OrderPart orderPart = new OrderPart(
                    quantity.get(i),
                    order,
                    products.get(i)
            );
            order.addPart(orderPart);
        }
        service.calculateTotalPrice(order);
        Assertions.assertEquals(order.getTotalPrice(), totalPrice);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Product("Apple", BigDecimal.valueOf(2.1)),
                                new Product("Pineapple", BigDecimal.valueOf(10.2)),
                                new Product("Orange", BigDecimal.valueOf(3.0))
                        ),
                        Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(1)),
                        BigDecimal.valueOf(25.5)),
                Arguments.of(
                        Arrays.asList(
                                new Product("Apple", BigDecimal.valueOf(4.1)),
                                new Product("Pineapple", BigDecimal.valueOf(12.2))
                        ),
                        Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(3)),
                        BigDecimal.valueOf(40.7)),
                Arguments.of(
                        Arrays.asList(
                                new Product("Apple", BigDecimal.valueOf(202.0)),
                                new Product("Pineapple", BigDecimal.valueOf(11.2)),
                                new Product("Orange", BigDecimal.valueOf(4.0))
                        ),
                        Arrays.asList(BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.valueOf(2)),
                        BigDecimal.valueOf(670.0)));
    }
}

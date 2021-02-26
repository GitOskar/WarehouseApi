package pl.warehouseapi.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.application.dto.mapper.WarehouseApiMapper;
import pl.warehouseapi.domain.agregate.Order;
import pl.warehouseapi.domain.agregate.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@SpringBootTest
public class WarehouseAPIMapperTest {

    @Autowired
    WarehouseApiMapper mapper;

    @ParameterizedTest(name = "{index} => year={0}, month={1}, dayOfMonth={2}, hour={3}, minute={4}, price={5}")
    @CsvSource({
            "2020, 11, 3, 12, 24, 5.2",
            "1952, 10, 2, 6, 11, 2.2",
            "2015, 11, 3, 23, 59, 1000000.01"
    })
    void assert_that_Order_to_OrderDto_mapping_works_good(int year, int month, int dayOfMonth, int hour, int minute, double price) {
        Order order = new Order(
                LocalDateTime.of(year, month, dayOfMonth, hour, minute),
                BigDecimal.valueOf(price));

        OrderDto orderDto = mapper.OrderToOrderDto(order);
        Assertions.assertEquals(orderDto.getTotalPrice(), order.getTotalPrice());
        Assertions.assertEquals(orderDto.getOrderDate(),
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @ParameterizedTest
    @MethodSource("provideParametersForOrderDtotoOrder")
    void assert_that_OrderDto_to_Order_mapping_works_good(String date, double price) {
        OrderDto orderDto = new OrderDto(date, price);
        Order order = mapper.OrderDtoToOrder(orderDto);
        Assertions.assertEquals(orderDto.getTotalPrice(), order.getTotalPrice());
        Assertions.assertEquals(
                orderDto.getOrderDate(),
                order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @ParameterizedTest
    @MethodSource("provideParametersForProduct")
    void assert_that_Product_to_ProductDto_mapping_works_good(String name, double price) {
        ProductDto product = mapper.ProductToProductDto( new Product(name, BigDecimal.valueOf(price)));

        Assertions.assertEquals(name, product.getName());
        Assertions.assertEquals(price, product.getPrice().doubleValue());
    }

    private static Stream<Arguments> provideParametersForOrderDtotoOrder() {
        return Stream.of(
                Arguments.of("2020-11-03 12:24", 2.1),
                Arguments.of("1952-10-02 06:11", 8.1),
                Arguments.of("2015-11-03 23:59", 192932.2)
        );
    }

    private static Stream<Arguments> provideParametersForProduct() {
        return Stream.of(
                Arguments.of("Apple", 2.1),
                Arguments.of("Pineapple", 10.0),
                Arguments.of("Motorcycle", 10000)
        );
    }
}

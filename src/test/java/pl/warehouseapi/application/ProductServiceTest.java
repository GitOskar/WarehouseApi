package pl.warehouseapi.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.application.service.ProductService;
import pl.warehouseapi.domain.agregate.Product;
import pl.warehouseapi.infrastructure.persistance.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    ProductRepository repository;

    @Autowired
    ProductService service;

    @ParameterizedTest
    @MethodSource("provideParametersProductUpdate")
    void assert_that_product_is_updated_correctly(String productName, BigDecimal productPrice) {
        Product oldProduct = new Product("Product that does not exist", BigDecimal.valueOf(1000000), null);
        oldProduct.setId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(oldProduct));

        Product newProduct = new Product(productName, productPrice, null);
        newProduct.setId(1);
        ProductDto updatedProduct = service.updateProduct(newProduct);

        Assertions.assertEquals(productPrice, updatedProduct.getPrice());
        Assertions.assertEquals(productName, updatedProduct.getName());
    }

    private static Stream<Arguments> provideParametersProductUpdate() {
        return Stream.of(
                Arguments.of("dark chocolate", BigDecimal.valueOf(3.2)),
                Arguments.of("chewing gum", BigDecimal.valueOf(1.2)),
                Arguments.of("lollipop", BigDecimal.valueOf(4.0))
        );
    }
}

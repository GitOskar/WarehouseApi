package pl.warehouseapi.application.service;

import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.domain.agregate.Product;
import java.util.List;

public interface ProductService {
    ProductDto addProduct(Product product);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(Product product);
}

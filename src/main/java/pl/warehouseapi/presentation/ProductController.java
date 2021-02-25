package pl.warehouseapi.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.application.service.ProductService;
import pl.warehouseapi.domain.agregate.Product;
import java.util.List;

@RestController("/warehouseapi")
public class ProductController {

    @Autowired
    private ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @PostMapping
    public ProductDto addProduct(Product product) {
        return service.addProduct(product);
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping
    public ProductDto updateProduct(Product product) {
        return service.updateProduct(product);
    }
}

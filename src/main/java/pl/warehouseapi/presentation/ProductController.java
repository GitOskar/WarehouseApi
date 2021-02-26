package pl.warehouseapi.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.application.service.ProductService;
import pl.warehouseapi.domain.agregate.Product;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/warehouseapi/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @PostMapping
    public ProductDto addProduct(@Valid @RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping
    public ProductDto updateProduct(@Valid @RequestBody Product product) {
        return service.updateProduct(product);
    }
}

package pl.warehouseapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.application.dto.mapper.WarehouseApiMapper;
import pl.warehouseapi.domain.agregate.Product;
import pl.warehouseapi.domain.exception.ProductNotFoundException;
import pl.warehouseapi.infrastructure.persistance.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private WarehouseApiMapper mapper;

    public ProductServiceImp(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto addProduct(Product product) {
        return mapper.ProductToProductDto(repository.save(product));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::ProductToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Product product) {
        Product productToUpdate = repository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException(product.getId()));

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());

        return mapper.ProductToProductDto(productToUpdate);
    }
}

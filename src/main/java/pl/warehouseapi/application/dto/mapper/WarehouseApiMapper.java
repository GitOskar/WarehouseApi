package pl.warehouseapi.application.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.warehouseapi.application.dto.OrderDto;
import pl.warehouseapi.application.dto.ProductDto;
import pl.warehouseapi.domain.agregate.Order;
import pl.warehouseapi.domain.agregate.Product;

@Mapper(componentModel = "spring")
public interface WarehouseApiMapper {
    WarehouseApiMapper mapper = Mappers.getMapper(WarehouseApiMapper.class);

    @Mapping(target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm")
    OrderDto OrderToOrderDto(Order order);

    @Mapping(target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm")
    Order OrderDtoToOrder(OrderDto orderDto);

    Product ProductDtoToProduct(ProductDto productDto);

    ProductDto ProductToProductDto(Product product);
}

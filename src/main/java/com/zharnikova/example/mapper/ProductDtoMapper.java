package com.zharnikova.example.mapper;

import com.zharnikova.example.dto.ProductDto;
import com.zharnikova.example.mapper.util.MappingUtil;
import com.zharnikova.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        MappingUtil.class
})
public interface ProductDtoMapper {
    @Mapping(target = "orders", qualifiedByName = {"MappingUtil", "getOrdersById"}, source = "orders")
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(target = "orders", qualifiedByName = {"MappingUtil", "getIdOrders"}, source = "orders")
    ProductDto productToproductDto(Product product);
}

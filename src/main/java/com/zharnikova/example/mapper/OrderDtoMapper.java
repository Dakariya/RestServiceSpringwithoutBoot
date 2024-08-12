package com.zharnikova.example.mapper;

import com.zharnikova.example.dto.OrderDto;
import com.zharnikova.example.mapper.util.MappingUtil;
import com.zharnikova.example.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        MappingUtil.class
})
public interface OrderDtoMapper {

    @Mapping(target = "products", qualifiedByName = {"MappingUtil", "getProductsById"}, source = "products")
    @Mapping(target = "customer", qualifiedByName = {"MappingUtil", "getCustomerById"}, source = "customerId")
    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "products", qualifiedByName = {"MappingUtil", "getIdProducts"}, source = "products")
    @Mapping(target = "customerId", qualifiedByName = {"MappingUtil", "getIdCustomer"}, source = "customer")
    OrderDto orderToOrderDto(Order order);
}

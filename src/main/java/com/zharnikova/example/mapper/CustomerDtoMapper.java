package com.zharnikova.example.mapper;

import com.zharnikova.example.dto.CustomerDto;
import com.zharnikova.example.mapper.util.MappingUtil;
import com.zharnikova.example.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        MappingUtil.class
})
public interface CustomerDtoMapper {
    @Mapping(target = "orders", qualifiedByName = {"MappingUtil", "getOrdersById"}, source = "orders")
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mapping(target = "orders", qualifiedByName = {"MappingUtil", "getIdOrders"}, source = "orders")
    CustomerDto customerToCustomerDto(Customer customer);
}

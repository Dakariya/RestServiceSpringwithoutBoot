package com.zharnikova.example.controller;

import com.zharnikova.example.dto.CustomerDto;
import com.zharnikova.example.mapper.CustomerDtoMapper;
import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerDtoMapper dtoMapper;

    public static Customer getTemplateCustomer(Integer id) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(id, id, new ArrayList<>(), new Customer(id, "Name" + id, new ArrayList<>()));
        orders.add(order);
        return new Customer(id, "Name" + id, orders);
    }

    public static List<Customer> customerList(int size) {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            customerList.add(getTemplateCustomer(i));
        }
        return customerList;
    }

    public static List<CustomerDto> customerDtoList(int size) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            customerDtos.add(new CustomerDto(i, "Name" + i, List.of(i)));
        }
        return customerDtos;
    }

    @Test
    void get() {
        int id = 1;
        CustomerDto customerDto = new CustomerDto(id, getTemplateCustomer(id).getName(), new ArrayList<>());
        when(customerService.get(Mockito.anyInt())).thenReturn(getTemplateCustomer(id));
        when(dtoMapper.customerToCustomerDto(any(Customer.class))).thenReturn(customerDto);
        ResponseEntity<CustomerDto> response = customerController.get(id);
        Mockito.verify(dtoMapper, times(1)).customerToCustomerDto(any(Customer.class));
        assertEquals(customerDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAll() {
        int size = 3;
        List<CustomerDto> customerDtos = customerDtoList(size);
        when(customerService.getAll()).thenReturn(customerList(size));

        doAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            return new CustomerDto(customer.getId(), customer.getName(), customer.getOrders().stream()
                    .map(Order::getId)
                    .toList());
        }).when(dtoMapper).customerToCustomerDto(any(Customer.class));

        ResponseEntity<List<CustomerDto>> response = customerController.getAll();
        Mockito.verify(dtoMapper, times(size)).customerToCustomerDto(any(Customer.class));
        assertEquals(customerDtos, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void save() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/customerDto.json")));
        doAnswer(invocation -> {
            CustomerDto customerDto = invocation.getArgument(0);
            return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getOrders().stream()
                    .map(i -> new Order(i, i, new ArrayList<>(), new Customer()))
                    .toList());
        }).when(dtoMapper).customerDtoToCustomer(any(CustomerDto.class));
        ResponseEntity<?> response = customerController.save(json);
        Mockito.verify(dtoMapper, times(1)).customerDtoToCustomer(any(CustomerDto.class));
        Mockito.verify(customerService, times(1)).save(any(Customer.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void doPut() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/customerDto.json")));
        doAnswer(invocation -> {
            CustomerDto customerDto = invocation.getArgument(0);
            return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getOrders().stream()
                    .map(i -> new Order(i, i, new ArrayList<>(), new Customer()))
                    .toList());
        }).when(dtoMapper).customerDtoToCustomer(any(CustomerDto.class));
        ResponseEntity<?> response = customerController.doPut(json);
        Mockito.verify(dtoMapper, times(1)).customerDtoToCustomer(any(CustomerDto.class));
        Mockito.verify(customerService, times(1)).update(any(Customer.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void doDelete() {
        int id = 1;
        ResponseEntity<?> response = customerController.doDelete(id);
        Mockito.verify(customerService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
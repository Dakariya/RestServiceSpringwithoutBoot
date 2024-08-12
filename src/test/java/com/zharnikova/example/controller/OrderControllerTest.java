package com.zharnikova.example.controller;

import com.zharnikova.example.dto.OrderDto;
import com.zharnikova.example.mapper.OrderDtoMapper;
import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.model.Product;
import com.zharnikova.example.service.impl.OrderServiceImpl;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderServiceImpl orderService;
    @Mock
    private OrderDtoMapper dtoMapper;

    public static Order getTemplateOrder(int id) {
        List<Product> products = new ArrayList<>();
        Product product = new Product(id, "Name" + id, (double) id, new ArrayList<>());
        products.add(product);
        return new Order(id, id, products, new Customer());
    }

    public static List<Order> getOrderList(int size) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            orderList.add(getTemplateOrder(i));
        }
        return orderList;
    }

    public static List<OrderDto> orderDTOList(int size) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            orderDtos.add(new OrderDto(i, i, List.of(i), i));
        }
        return orderDtos;
    }

    @Test
    void get() {
        int id = 1;
        OrderDto orderDto = new OrderDto(id, id, new ArrayList<>(), id);
        when(orderService.get(Mockito.anyInt())).thenReturn(getTemplateOrder(id));
        when(dtoMapper.orderToOrderDto(any(Order.class))).thenReturn(orderDto);
        ResponseEntity<OrderDto> response = orderController.get(id);
        Mockito.verify(dtoMapper, times(1)).orderToOrderDto(any(Order.class));
        assertEquals(orderDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAll() {
        int size = 3;
        List<OrderDto> expectedOrderDtos = orderDTOList(size);
        List<Order> orders = getOrderList(size);

        when(orderService.getAll()).thenReturn(orders);

        doAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            return new OrderDto(order.getId(), order.getNumber(), order.getProducts().stream()
                    .map(Product::getId)
                    .collect(Collectors.toList()), order.getId());
        }).when(dtoMapper).orderToOrderDto(any(Order.class));

        ResponseEntity<List<OrderDto>> response = orderController.getAll();

        Mockito.verify(dtoMapper, times(size)).orderToOrderDto(any(Order.class));
        assertEquals(expectedOrderDtos, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void save() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/orderDto.json")));
        doAnswer(invocation -> {
            OrderDto orderDTO = invocation.getArgument(0);
            return new Order(orderDTO.getId(), orderDTO.getNumber(), orderDTO.getProducts().stream()
                    .map(i -> new Product(i, "Name" + i, (double)i, new ArrayList<>()))
                    .toList(), new Customer(orderDTO.getCustomerId(), "SomeName", new ArrayList<>()));
        }).when(dtoMapper).orderDtoToOrder(any(OrderDto.class));
        ResponseEntity<?> response = orderController.save(json);
        Mockito.verify(dtoMapper, times(1)).orderDtoToOrder(any(OrderDto.class));
        Mockito.verify(orderService, times(1)).save(any(Order.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void doPut() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/orderDto.json")));
        doAnswer(invocation -> {
            OrderDto orderDTO = invocation.getArgument(0);
            return new Order(orderDTO.getId(), orderDTO.getNumber(), orderDTO.getProducts().stream()
                    .map(i -> new Product(i, "Name" + i, (double)i, new ArrayList<>()))
                    .toList(), new Customer(orderDTO.getCustomerId(), "SomeName", new ArrayList<>()));
        }).when(dtoMapper).orderDtoToOrder(any(OrderDto.class));
        ResponseEntity<?> response = orderController.doPut(json);
        Mockito.verify(dtoMapper, times(1)).orderDtoToOrder(any(OrderDto.class));
        Mockito.verify(orderService, times(1)).update(any(Order.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void doDelete() {
        int id = 1;
        ResponseEntity<?> response = orderController.doDelete(id);
        Mockito.verify(orderService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
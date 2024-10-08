package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Order;
import com.zharnikova.example.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zharnikova.example.controller.OrderControllerTest.getOrderList;
import static com.zharnikova.example.controller.OrderControllerTest.getTemplateOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void getAll() {
        when(orderRepository.findAll()).thenReturn(getOrderList(5));
        List<Order> getAllOrders = orderService.getAll();
        Assertions.assertEquals(getAllOrders, getOrderList(5));
    }

    @Test
    void get() {
        when(orderRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getTemplateOrder(1)));
        Order getOrder = orderService.get(1);
        Assertions.assertEquals(getOrder, getTemplateOrder(1));
    }

    @Test
    void save() {
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(getTemplateOrder(1));
        Order saveOrder = orderService.save(getTemplateOrder(1));
        Assertions.assertEquals(saveOrder, getTemplateOrder(1));
    }

    @Test
    public void testUpdate() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        orderService.update(order);
        verify(orderRepository).save(order);
    }


    @Test
    void delete() {
        orderService.delete(1);
        verify(orderRepository).deleteById(1);
    }
}
package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Order;
import com.zharnikova.example.repository.OrderRepository;
import com.zharnikova.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zharnikova.example.service.impl.ProductServiceImpl.INVALID_ORDER_ID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(INVALID_ORDER_ID));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        Order updateOrder = get(order.getId());
        updateOrder.setNumber(order.getNumber());
        updateOrder.setProducts(order.getProducts());
        orderRepository.save(updateOrder);

    }

    @Override
    public void delete(int orderId) {
        orderRepository.deleteById(orderId);
    }
}

package com.zharnikova.example.service;

import com.zharnikova.example.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order get(Integer id);
    Order save(Order order);

    void update(Order order);

    void delete(int orderId);
}

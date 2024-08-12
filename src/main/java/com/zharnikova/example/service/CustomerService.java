package com.zharnikova.example.service;

import com.zharnikova.example.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer get(Integer id);
    Customer save (Customer customer);
    void update(Customer customer);
    void delete(Integer customerId);
}

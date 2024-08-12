package com.zharnikova.example.service;

import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product get(Integer id);
    Product save(Product product);
    void update(Product product);
    void delete(Integer productId);

}

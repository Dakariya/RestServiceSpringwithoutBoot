package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Product;
import com.zharnikova.example.repository.ProductRepository;
import com.zharnikova.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public static final String INVALID_ID = "Invalid id";
    public static final String INVALID_ORDER_ID = "Invalid order id";
    public static final String SQL_QUERY_FAILED = "Sql query failed...";
    public static final String INVALID_PRODUCT_ID = "Invalid item id";
    public static final String INVALID_CUSTOMER_ID = "Invalid buyer id";

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(Integer id) {
        return productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(INVALID_ORDER_ID));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        Product updateproduct = get(product.getId());
        updateproduct.setName(product.getName());
        updateproduct.setPrice(product.getPrice());
        updateproduct.setOrders(product.getOrders());
        productRepository.save(updateproduct);

    }

    @Override
    public void delete(Integer productId) {
        productRepository.deleteById(productId);

    }
}

package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Product;
import com.zharnikova.example.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zharnikova.example.controller.ProductControllerTest.getProductList;
import static com.zharnikova.example.controller.ProductControllerTest.getTemplateProduct;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAll() {
        when(productRepository.findAll()).thenReturn(getProductList(5));
        List<Product> getAllItem = productService.getAll();
        Assertions.assertEquals(getAllItem, getProductList(5));
    }

    @Test
    void get() {
        when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getTemplateProduct(1)));
        Product getProduct = productService.get(1);
        Assertions.assertEquals(getProduct, getTemplateProduct(1));
    }

    @Test
    void save() {
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(getTemplateProduct(1));
        Product saveProduct = productService.save(getTemplateProduct(1));
        Assertions.assertEquals(saveProduct, getTemplateProduct(1));
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        productService.update(product);
        verify(productRepository).save(product);
    }

    @Test
    void delete() {
        productService.delete(1);
        verify(productRepository).deleteById(1);
    }

}
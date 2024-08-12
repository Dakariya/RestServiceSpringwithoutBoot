package com.zharnikova.example.controller;

import com.zharnikova.example.dto.ProductDto;
import com.zharnikova.example.mapper.ProductDtoMapper;
import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.model.Product;
import com.zharnikova.example.service.impl.ProductServiceImpl;
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
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductServiceImpl productService;
    @Mock
    private ProductDtoMapper dtoMapper;

    public static Product getTemplateProduct(Integer id) {
        List<Order> orders = new ArrayList<>();
        return new Product(id, "Name" + id, (double) id, orders);
    }

    public static List<Product> getProductList(int size) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            productList.add(getTemplateProduct(i));
        }
        return productList;
    }

    public static List<ProductDto> productDtoList(int size) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            productDtos.add(new ProductDto(i, "Name" + i, (double) i, new ArrayList<>()));
        }
        return productDtos;
    }

    @Test
    void get() {
        int id = 1;
        ProductDto productDto = new ProductDto(id, getTemplateProduct(id).getName(),(double) id, new ArrayList<>());
        when(productService.get(Mockito.anyInt())).thenReturn(getTemplateProduct(id));
        when(dtoMapper.productToproductDto(any(Product.class))).thenReturn(productDto);
        ResponseEntity<ProductDto> response = productController.get(id);
        Mockito.verify(dtoMapper, times(1)).productToproductDto(any(Product.class));
        assertEquals(productDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAll() {
        int size = 3;
        List<ProductDto> productDtos = productDtoList(size);
        when(productService.getAll()).thenReturn(getProductList(size));

        doAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getOrders().stream()
                    .map(Order::getId)
                    .toList());
        }).when(dtoMapper).productToproductDto(any(Product.class));

        ResponseEntity<List<ProductDto>> response = productController.getAll();
        Mockito.verify(dtoMapper, times(size)).productToproductDto(any(Product.class));
        assertEquals(productDtos, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void save() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/productDto.json")));
        doAnswer(invocation -> {
            ProductDto productDto = invocation.getArgument(0);
            return new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getOrders().stream()
                    .map(i -> new Order(i, i, new ArrayList<>(), new Customer()))
                    .toList());
        }).when(dtoMapper).productDtoToProduct(any(ProductDto.class));
        ResponseEntity<?> response = productController.save(json);
        Mockito.verify(dtoMapper, times(1)).productDtoToProduct(any(ProductDto.class));
        Mockito.verify(productService, times(1)).save(any(Product.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void doPut() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/productDto.json")));
        doAnswer(invocation -> {
            ProductDto productDto = invocation.getArgument(0);
            return new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getOrders().stream()
                    .map(i -> new Order(i, i, new ArrayList<>(), new Customer()))
                    .toList());
        }).when(dtoMapper).productDtoToProduct(any(ProductDto.class));
        ResponseEntity<?> response = productController.doPut(json);
        Mockito.verify(dtoMapper, times(1)).productDtoToProduct(any(ProductDto.class));
        Mockito.verify(productService, times(1)).update(any(Product.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void doDelete() {
        int id = 1;
        ResponseEntity<?> response = productController.doDelete(id);
        Mockito.verify(productService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
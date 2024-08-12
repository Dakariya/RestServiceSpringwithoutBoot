package com.zharnikova.example.mapper.util;

import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.model.Product;
import com.zharnikova.example.repository.CustomerRepository;
import com.zharnikova.example.repository.OrderRepository;
import com.zharnikova.example.repository.ProductRepository;
import com.zharnikova.example.service.OrderService;
import com.zharnikova.example.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.zharnikova.example.service.impl.ProductServiceImpl.INVALID_ORDER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MappingUtilTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private MappingUtil mappingUtil;

    private Order order;
    private Product product;
    private Customer customer;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1);

        product = new Product();
        product.setId(1);

        customer = new Customer();
        customer.setId(1);
    }

    @Test
    void testGetIdOrders() {
        List<Order> orders = Arrays.asList(order);
        List<Integer> idOrders = mappingUtil.getIdOrders(orders);
        assertEquals(Arrays.asList(1), idOrders);
    }

    @Test
    void testGetOrdersById() {
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        List<Order> orders = mappingUtil.getOrdersById(Arrays.asList(1));
        assertEquals(Arrays.asList(order), orders);
    }

    @Test
    void testGetProductsById() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        List<Product> products = mappingUtil.getProductsById(Arrays.asList(1));
        assertEquals(Arrays.asList(product), products);
    }

    @Test
    void testGetIdProducts() {
        List<Product> products = Arrays.asList(product);
        List<Integer> idProducts = mappingUtil.getIdProducts(products);
        assertEquals(Arrays.asList(1), idProducts);
    }

    @Test
    void testGetIdCustomer() {
        Integer idCustomer = mappingUtil.getIdCustomer(customer);
        assertEquals(1, idCustomer);
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        Customer foundCustomer = mappingUtil.getCustomerById(1);
        assertEquals(customer, foundCustomer);
    }

    @Test
    public void testGetOrdersByIdInvalidOrderId() {
        List<Integer> idOrders = new ArrayList<>();
        idOrders.add(1);
        idOrders.add(2);
        idOrders.add(-1); // Недействительный идентификатор заказа

        assertThrows(IllegalArgumentException.class, () -> mappingUtil.getOrdersById(idOrders));
    }

    @Test
    public void testGetProductsByIdInvalidProductId() {
        List<Integer> idProducts = new ArrayList<>();
        idProducts.add(1);
        idProducts.add(2);
        idProducts.add(-1); // Недействительный идентификатор продукта

        assertThrows(IllegalArgumentException.class, () -> mappingUtil.getProductsById(idProducts));
    }

    @Test
    public void testGetCustomerByIdInvalidCustomerId() {
        Integer idCustomer = -1; // Недействительный идентификатор клиента

        assertThrows(IllegalArgumentException.class, () -> mappingUtil.getCustomerById(idCustomer));
    }

    @Test
    public void testGetOrdersByIdThrowsException() {
        // Given
        List<Integer> idOrders = Arrays.asList(1, 2, 3);
        when(orderRepository.findById(1)).thenReturn(Optional.of(new Order()));
        when(orderRepository.findById(2)).thenReturn(Optional.empty()); // This will trigger the exception

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            mappingUtil.getOrdersById(idOrders);
        }, INVALID_ORDER_ID);
    }
}
package com.zharnikova.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private final int id = 1;
    private final int number = 1111;
    private final List<Product> products = getTempProducts();
    private final Customer customer = new Customer();

    @Test
    void getId() {
        Order order = new Order(id, number, products, customer);
        assertEquals(id, order.getId());
    }

    @Test
    void setId() {
        Order order = new Order();
        order.setId(id);
        assertEquals(id, order.getId());
    }

    @Test
    void getNumber() {
        Order order = new Order(id, number, products, customer);
        assertEquals(number, order.getNumber());
    }

    @Test
    void setNumber() {
        Order order = new Order();
        order.setNumber(number);
        assertEquals(number, order.getNumber());
    }

    @Test
    void getProducts() {
        Order order = new Order(id, number, products, customer);
        assertEquals(products, order.getProducts());
    }

    @Test
    void setProducts() {
        Order order = new Order();
        order.setProducts(products);
        assertEquals(products, order.getProducts());
    }

    static List<Product> getTempProducts() {
        int size = 5;
        List<Product> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Product product = new Product(i, "SomeItem" + i, i * 2.0, new ArrayList<>());
            items.add(product);
        }
        return items;
    }

    @Test
    void getCustomer() {
        Order order = new Order(id, number, products, customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void setCustomer() {
        Order order = new Order();
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void testEqualsWithDifferentValues() {
        Order order1 = new Order(1, 10, null, null);
        Order order2 = new Order(2, 20, null, null);
        assertFalse(order1.equals(order2));
    }

    @Test
    public void testEqualsWithNull() {
        Order order = new Order(1, 10, null, null);
        assertFalse(order.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(new Order(1, 10, null, null).equals(new Object()));
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        Order order1 = new Order(1, 10, null, null);
        Order order2 = new Order(2, 20, null, null);
        assertNotEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    public void testHashCodeWithNull() {
        Order order = new Order(1, 10, null, null);
        assertNotEquals(order.hashCode(), 0);
    }

    @Test
    public void testToString() {
        Order order = new Order(1, 10, null, null);
        String expected = "Order{id=1, number=10, products=null, customer=null}";
        String actual = order.toString();
        assertEquals(expected, actual);
    }
}
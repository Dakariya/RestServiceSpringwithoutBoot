package com.zharnikova.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zharnikova.example.model.CustomerTest.getTempOrders;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private final int id = 1;
    private final String name = "someName";
    private final Double price = 10.0;
    public List<Order> orders = getTempOrders();

    @Test
    void getId() {
        Product product = new Product(id, name, price, orders);
        assertEquals(id, product.getId());
    }

    @Test
    void setId() {
        Product product = new Product();
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    void getName() {
        Product product = new Product(id, name, price, orders);
        assertEquals(name, product.getName());
    }

    @Test
    void setName() {
        Product product = new Product();
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    void getPrice() {
        Product product = new Product(id, name, price, orders);
        assertEquals(price, product.getPrice());
    }

    @Test
    void setPrice() {
        Product product = new Product();
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }

    @Test
    void getOrders() {
        Product product = new Product(id, name, price, orders);
        assertEquals(orders, product.getOrders());
    }

    @Test
    void setOrders() {
        Product product = new Product();
        product.setOrders(orders);
        assertEquals(orders, product.getOrders());
    }

    @Test
    public void testEquals() {
        Product product1 = new Product(1, "Книга", 10.0, null);
        Product product2 = new Product(1, "Книга", 10.0, null);
        assertTrue(product1.equals(product2));
    }

    @Test
    public void testEqualsWithNull() {
        Product product = new Product(1, "Книга", 10.0, null);
        assertFalse(product.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(new Product(1, "Книга", 10.0, null).equals(new Object()));
    }

    @Test
    public void testEqualsWithDifferentValues() {
        Product product1 = new Product(1, "Книга", 10.0, null);
        Product product2 = new Product(2, "Ручка", 5.0, null);
        assertFalse(product1.equals(product2));
    }

    @Test
    public void testHashCode() {
        Product product1 = new Product(1, "Книга", 10.0, null);
        Product product2 = new Product(2, "Ручка", 5.0, null);
        assertNotEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    public void testToString() {
        Product product = new Product(1, "Книга", 10.0, null);
        String expected = "Product{id=1, name='Книга', price=10.0, orders=null}";
        assertEquals(expected, product.toString());
    }

}
package com.zharnikova.example.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CustomerTest {
    private final int id = 1;
    private final String name = "someName";

    @Test
    void getId() {
        Customer customer = new Customer(id, name, new ArrayList<>());
        assertEquals(id, customer.getId());
    }

    @Test
    void setId() {
        Customer customer = new Customer();
        customer.setId(id);
        assertEquals(id, customer.getId());
    }

    @Test
    void getName() {
        Customer customer = new Customer(id, name, new ArrayList<>());
        assertEquals(name, customer.getName());
    }

    @Test
    void setName() {
        Customer customer = new Customer();
        customer.setName(name);
        assertEquals(name, customer.getName());
    }

    @Test
    void getOrders() {
        Customer customer = new Customer(id, name, getTempOrders());
        assertEquals(getTempOrders(), customer.getOrders());
    }

    @Test
    void setOrders() {
        Customer customer = new Customer();
        customer.setOrders(getTempOrders());
        assertEquals(getTempOrders(), customer.getOrders());
    }

    static List<Order> getTempOrders() {
        int size = 5;
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Order order = new Order(i, i * 2, new ArrayList<>(), new Customer());
            orders.add(order);
        }
        return orders;
    }

    @Test
    public void testEquals() {
        Customer customer1 = new Customer(1, "John Doe", null);
        Customer customer2 = new Customer(1, "John Doe", null);
        assertTrue(customer1.equals(customer2));
    }

    @Test
    public void testEqualsWithNull() {
        Customer customer = new Customer(1, "John Doe", null);
        assertFalse(customer.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(new Customer(1, "John Doe", null).equals(new Object()));
    }

    @Test
    public void testEqualsWithDifferentValues() {
        Customer customer1 = new Customer(1, "John Doe", null);
        Customer customer2 = new Customer(2, "Jane Doe", null);
        assertFalse(customer1.equals(customer2));
    }



    @Test
    public void testHashCode() {
        Customer customer1 = new Customer(1, "John Doe", null);
        Customer customer2 = new Customer(1, "John Doe", null);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(1, "John Doe", null);
        String expected = "Customer{id=1, name='John Doe', orders=null}";
        assertEquals(expected, customer.toString());
    }
}
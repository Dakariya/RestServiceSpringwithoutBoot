package com.zharnikova.example.dto;

import com.zharnikova.example.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDtoTest {

    private final int id = 1;
    private final String name = "someName";

    @Test
    void testGetId() {
        CustomerDto customerDto = new CustomerDto(id, name, new ArrayList<>());
        assertEquals(id, customerDto.getId());
    }

    @Test
    void testSetId() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        assertEquals(id, customerDto.getId());
    }

    @Test
    void testTestGetName() {
        CustomerDto customerDto = new CustomerDto(id, name, new ArrayList<>());
        assertEquals(name, customerDto.getName());
    }

    @Test
    void testTestSetName() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(name);
        assertEquals(name, customerDto.getName());
    }

    @Test
    void testGetOrders() {
        CustomerDto customerDto = new CustomerDto(id, name, getTempListInteger());
        assertEquals(getTempListInteger(), customerDto.getOrders());
    }

    @Test
    void testSetOrders() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setOrders(getTempListInteger());
        assertEquals(getTempListInteger(), customerDto.getOrders());
    }

    static List<Integer> getTempListInteger() {
        int size = 5;
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            integers.add(i);
        }
        return integers;
    }

    @Test
    public void testEqualsWithDifferentValues() {
        CustomerDto customerDto1 = new CustomerDto(1, "John Doe", List.of(1, 2, 3));
        CustomerDto customerDto2 = new CustomerDto(2, "Jane Doe", List.of(4, 5, 6));
        assertFalse(customerDto1.equals(customerDto2));
    }

    @Test
    public void testEqualsWithNull() {
        CustomerDto customerDto = new CustomerDto(1, "John Doe", List.of(1, 2, 3));
        assertFalse(customerDto.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(new CustomerDto(1, "John Doe", null).equals(new Object()));
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        CustomerDto customerDto1 = new CustomerDto(1, "John Doe", List.of(1, 2, 3));
        CustomerDto customerDto2 = new CustomerDto(2, "Jane Doe", List.of(4, 5, 6));
        assertNotEquals(customerDto1.hashCode(), customerDto2.hashCode());
    }

    @Test
    public void testHashCodeWithNull() {
        CustomerDto customerDto = new CustomerDto(1, "John Doe", List.of(1, 2, 3));
        assertNotEquals(customerDto.hashCode(), 0);
    }

    @Test
    public void testHashCodeWithDifferentClass() {
        Object object = new Object();
        CustomerDto customerDto = new CustomerDto(1, "John Doe", null);
        assertNotEquals(customerDto.hashCode(), object.hashCode());
    }

    @Test
    public void testToString() {
        CustomerDto customerDto = new CustomerDto(1, "John Doe", List.of(1, 2, 3));
        String expected = "CustomerDto{id=1, name='John Doe', orders=[1, 2, 3]}";
        String actual = customerDto.toString();
        assertEquals(expected, actual);
    }
}
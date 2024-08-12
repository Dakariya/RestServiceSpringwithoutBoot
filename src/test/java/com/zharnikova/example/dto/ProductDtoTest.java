package com.zharnikova.example.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zharnikova.example.dto.CustomerDtoTest.getTempListInteger;
import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {
    private final int id = 1;
    private final String name = "someName";
    private final Double price = 10.0;
    public List<Integer> orders = getTempListInteger();

    @Test
    void getId() {
        ProductDto productDto = new ProductDto(id, name, price, orders);
        assertEquals(id, productDto.getId());
    }

    @Test
    void setId() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        assertEquals(id, productDto.getId());
    }

    @Test
    void getName() {
        ProductDto productDto = new ProductDto(id, name, price, orders);
        assertEquals(name, productDto.getName());
    }

    @Test
    void setName() {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        assertEquals(name, productDto.getName());
    }

    @Test
    void getPrice() {
        ProductDto item = new ProductDto(id, name, price, orders);
        assertEquals(price, item.getPrice());
    }

    @Test
    void setPrice() {
        ProductDto productDto = new ProductDto();
        productDto.setPrice(price);
        assertEquals(price, productDto.getPrice());
    }

    @Test
    void getOrders() {
        ProductDto productDto = new ProductDto(id, name, price, orders);
        assertEquals(orders, productDto.getOrders());
    }

    @Test
    void setOrders() {
        ProductDto productDto = new ProductDto();
        productDto.setOrders(orders);
        assertEquals(orders, productDto.getOrders());
    }

    @Test
    public void testEqualsWithDifferentValues() {
        ProductDto productDto1 = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        ProductDto productDto2 = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        assertTrue(productDto1.equals(productDto2));
    }

    @Test
    public void testEqualsWithNull() {
        ProductDto productDto = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        assertFalse(productDto.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Object object = new Object();
        ProductDto productDto = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        assertFalse(productDto.equals(object));
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        ProductDto productDto1 = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        ProductDto productDto2 = new ProductDto(2, "Ручка", 5.0, List.of(4, 5, 6));
        assertNotEquals(productDto1.hashCode(), productDto2.hashCode());
    }

    @Test
    public void testHashCodeWithNull() {
        ProductDto productDto = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        assertNotEquals(productDto.hashCode(), 0);
    }

    @Test
    public void testHashCodeWithDifferentClass() {
        Object object = new Object();
        ProductDto productDto = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        assertNotEquals(productDto.hashCode(), object.hashCode());
    }

    @Test
    public void testToString() {
        ProductDto productDto = new ProductDto(1, "Книга", 10.0, List.of(1, 2, 3));
        String expected = "ProductDto{id=1, name='Книга', price=10.0, orders=[1, 2, 3]}";
        String actual = productDto.toString();
        assertEquals(expected, actual);
    }

}
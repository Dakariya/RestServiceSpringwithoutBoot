package com.zharnikova.example.dto;

import com.zharnikova.example.mapper.OrderDtoMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zharnikova.example.dto.CustomerDtoTest.getTempListInteger;
import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    private final int id = 1;
    private final int number = 1111;
    private final List<Integer> products = getTempListInteger();
    private final int idCustomer = 1;


    @Test
    void getId() {
        OrderDto orderDto = new OrderDto(id, number, products, idCustomer);
        assertEquals(id, orderDto.getId());
    }

    @Test
    void setId() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        assertEquals(id, orderDto.getId());
    }

    @Test
    void getNumber() {
        OrderDto orderDto = new OrderDto(id, number, products, idCustomer);
        assertEquals(number, orderDto.getNumber());
    }

    @Test
    void setNumber() {
        OrderDto orderDto = new OrderDto();
        orderDto.setNumber(number);
        assertEquals(number, orderDto.getNumber());
    }

    @Test
    void getItems() {
        OrderDto order = new OrderDto(id, number, products, idCustomer);
        assertEquals(products, order.getProducts());
    }

    @Test
    void setItems() {
        OrderDto orderDto = new OrderDto();
        orderDto.setProducts(products);
        assertEquals(products, orderDto.getProducts());
    }

    @Test
    public void testEqualsWithDifferentValues() {
        OrderDto orderDto1 = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        OrderDto orderDto2 = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        assertTrue(orderDto1.equals(orderDto2));
    }

    @Test
    public void testEqualsWithNull() {
        OrderDto orderDto = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        assertFalse(orderDto.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Object object = new Object();
        OrderDto orderDto = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        assertFalse(orderDto.equals(object));
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        OrderDto orderDto1 = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        OrderDto orderDto2 = new OrderDto(2, 3, List.of(4, 5, 6), 5);
        assertNotEquals(orderDto1.hashCode(), orderDto2.hashCode());
    }

    @Test
    public void testHashCodeWithNull() {
        OrderDto orderDto = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        assertNotEquals(orderDto.hashCode(), 0);
    }

    @Test
    public void testHashCodeWithDifferentClass() {
        Object object = new Object();
        OrderDto orderDto = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        assertNotEquals(orderDto.hashCode(), object.hashCode());
    }

    @Test
    public void testToString() {
        OrderDto orderDto = new OrderDto(1, 2, List.of(1, 2, 3), 4);
        String expected = "OrderDto{id=1, number=2, products=[1, 2, 3], customerId=4}";
        String actual = orderDto.toString();
        assertEquals(expected, actual);
    }
}
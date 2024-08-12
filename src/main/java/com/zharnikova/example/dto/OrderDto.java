package com.zharnikova.example.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Objects;

@JsonSerialize
public class OrderDto {
    private Integer id;
    private Integer number;
    private List<Integer> products;
    private Integer customerId;

    public OrderDto() {
    }

    public OrderDto(Integer id, Integer number, List<Integer> products, Integer customerId) {
        this.id = id;
        this.number = number;
        this.products = products;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderDto orderDto = (OrderDto) object;
        return Objects.equals(id, orderDto.id) && Objects.equals(number, orderDto.number) && Objects.equals(products, orderDto.products) && Objects.equals(customerId, orderDto.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, products, customerId);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", number=" + number +
                ", products=" + products +
                ", customerId=" + customerId +
                '}';
    }

}

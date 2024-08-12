package com.zharnikova.example.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="number")
    private Integer number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="order_products",
            joinColumns = {@JoinColumn(name="id_order")},
            inverseJoinColumns = {@JoinColumn(name="id_product")})
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    public Order() {
    }

    public Order(Integer id, Integer number, List<Product> products, Customer customer) {
        this.id = id;
        this.number = number;
        this.products = products;
        this.customer = customer;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return Objects.equals(id, order.id) && Objects.equals(number, order.number) && Objects.equals(products, order.products) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, products, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}

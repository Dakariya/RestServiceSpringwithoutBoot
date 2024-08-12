package com.zharnikova.example.mapper.util;

import com.zharnikova.example.model.Customer;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.model.Product;
import com.zharnikova.example.repository.CustomerRepository;
import com.zharnikova.example.repository.OrderRepository;
import com.zharnikova.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

import static com.zharnikova.example.service.impl.ProductServiceImpl.*;

@Named("MappingUtil")
@Component
public class MappingUtil {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public MappingUtil(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;

    }

    @Named("getIdOrders")
    public List<Integer> getIdOrders(List<Order> orders) {
        List<Integer> idOrders = new ArrayList<>();
        if (orders != null) {
            for (Order order : orders) {
                idOrders.add(order.getId());
            }
        }
        return idOrders;
    }

    @Named("getOrdersById")
    public List<Order> getOrdersById(List<Integer> idOrders){
        List<Order> orders = new ArrayList<>();
        if(idOrders!=null){
            for(int id : idOrders){
                Order order=orderRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(INVALID_ORDER_ID));
                if(order!=null){
                    orders.add(order);
                } else{
                    throw new IllegalArgumentException(INVALID_ORDER_ID);
                }
            }
        }
        return orders;
    }

    @Named("getProductsById")
    public List<Product> getProductsById(List<Integer> idProducts){
        List<Product> products = new ArrayList<>();
        if(idProducts!=null){
            for(int id : idProducts){
                Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(INVALID_ORDER_ID));
                if(product!=null){
                    products.add(product);
                } else{
                    throw new IllegalArgumentException(INVALID_PRODUCT_ID);
                }
            }
        }
        return products;
    }

    @Named("getIdProducts")
    public List<Integer> getIdProducts(List<Product> products){
        List<Integer> idProducts = new ArrayList<>();
        if(products!=null){
            for(Product product:products){
                idProducts.add(product.getId());
            }
        }
        return idProducts;
    }

    @Named("gerIdCustomer")
    public Integer getIdCustomer(Customer customer){
        return customer.getId();
    }

    @Named("getCustomerById")
    public Customer getCustomerById(Integer idCustomer){
        Customer customer = new Customer();
        if(idCustomer!=null){
            customer=customerRepository.findById(idCustomer).orElseThrow(()-> new IllegalArgumentException(INVALID_CUSTOMER_ID));
            if(customer==null){
                throw new IllegalArgumentException(INVALID_CUSTOMER_ID);
            }
        }
        return customer;
    }
}


package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Customer;
import com.zharnikova.example.repository.CustomerRepository;
import com.zharnikova.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zharnikova.example.service.impl.ProductServiceImpl.INVALID_ORDER_ID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer get(Integer id) {
        return customerRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(INVALID_ORDER_ID));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        Customer updateCustomer = get(customer.getId());
        updateCustomer.setName(customer.getName());
        updateCustomer.setOrders(customer.getOrders());
        customerRepository.save(updateCustomer);
    }

    @Override
    public void delete(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}

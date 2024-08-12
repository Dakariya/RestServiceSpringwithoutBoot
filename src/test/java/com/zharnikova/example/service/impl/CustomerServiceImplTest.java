package com.zharnikova.example.service.impl;

import com.zharnikova.example.model.Customer;
import com.zharnikova.example.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zharnikova.example.controller.CustomerControllerTest.customerList;
import static com.zharnikova.example.controller.CustomerControllerTest.getTemplateCustomer;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getAll() {
        when(customerRepository.findAll()).thenReturn(customerList(5));
        List<Customer> getAllCustomer = customerService.getAll();
        Assertions.assertEquals(getAllCustomer, customerList(5));
    }

    @Test
    void get() {
        when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getTemplateCustomer(1)));
        Customer getCustomer = customerService.get(1);
        Assertions.assertEquals(getCustomer, getTemplateCustomer(1));
    }

    @Test
    void save() {
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(getTemplateCustomer(1));
        Customer saveCustomer = customerService.save(getTemplateCustomer(1));
        Assertions.assertEquals(saveCustomer, getTemplateCustomer(1));
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer(1, "John Doe", null);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        customer.setName("John Smith");
        customerService.update(customer);

        verify(customerRepository, times(1)).save(customer);
        assertEquals("John Smith", customer.getName());
    }

    @Test
    void delete() {
        customerService.delete(1);
        verify(customerRepository).deleteById(1);
    }
}
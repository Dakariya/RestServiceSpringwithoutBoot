package com.zharnikova.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zharnikova.example.dto.CustomerDto;
import com.zharnikova.example.mapper.CustomerDtoMapper;
import com.zharnikova.example.model.Customer;
import com.zharnikova.example.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final CustomerDtoMapper customerDtoMapper;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CustomerController(CustomerServiceImpl customerService, CustomerDtoMapper customerDtoMapper){
        this.customerService=customerService;
        this.customerDtoMapper=customerDtoMapper;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> get(@PathVariable("id") Integer id){
        Customer customer = customerService.get(id);
        CustomerDto customerDto = null;
        if(customer!=null){
            customerDto = customerDtoMapper.customerToCustomerDto(customer);
        }
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = customerService.getAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(customerDtoMapper.customerToCustomerDto(customer));
        }
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody String json) throws JsonProcessingException{
        CustomerDto customerDto = mapper.readValue(json, CustomerDto.class);
        Customer customer = customerDtoMapper.customerDtoToCustomer(customerDto);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doPut(@RequestBody String json) throws JsonProcessingException{
        CustomerDto customerDto = mapper.readValue(json, CustomerDto.class);
        Customer customer = customerDtoMapper.customerDtoToCustomer(customerDto);
        customerService.update(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> doDelete(@RequestParam("id") Integer id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

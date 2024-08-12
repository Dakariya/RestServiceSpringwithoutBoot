package com.zharnikova.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zharnikova.example.dto.OrderDto;
import com.zharnikova.example.mapper.OrderDtoMapper;
import com.zharnikova.example.model.Order;
import com.zharnikova.example.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final OrderDtoMapper orderDtoMapper;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public OrderController(OrderServiceImpl orderService, OrderDtoMapper orderDtoMapper){
        this.orderService=orderService;
        this.orderDtoMapper=orderDtoMapper;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> get(@PathVariable("id") Integer id){
        Order order = orderService.get(id);
        OrderDto orderDto = null;
        if(order!=null){
            orderDto = orderDtoMapper.orderToOrderDto(order);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orders = orderService.getAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(orderDtoMapper.orderToOrderDto(order));
        }
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody String json) throws JsonProcessingException {
        OrderDto orderDto = mapper.readValue(json, OrderDto.class);
        Order order = orderDtoMapper.orderDtoToOrder(orderDto);
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doPut(@RequestBody String json) throws JsonProcessingException {
        OrderDto orderDto = mapper.readValue(json, OrderDto.class);
        Order order = orderDtoMapper.orderDtoToOrder(orderDto);
        orderService.update(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> doDelete(@RequestParam("id") Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

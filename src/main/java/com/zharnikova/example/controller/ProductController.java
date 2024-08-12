package com.zharnikova.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zharnikova.example.dto.ProductDto;
import com.zharnikova.example.mapper.ProductDtoMapper;
import com.zharnikova.example.model.Product;
import com.zharnikova.example.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductServiceImpl productService;
    private final ProductDtoMapper productDtoMapper;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ProductController(ProductServiceImpl productService, ProductDtoMapper productDtoMapper){
        this.productService=productService;
        this.productDtoMapper=productDtoMapper;
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> get(@PathVariable("id") Integer id){
        Product product = productService.get(id);
        ProductDto productDto = null;
        if(product != null){
            productDto=productDtoMapper.productToproductDto(product);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAll(){
        List<Product> products = productService.getAll();
        List<ProductDto> productDtos= new ArrayList<>();
        for(Product product : products){
            productDtos.add(productDtoMapper.productToproductDto(product));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody String json) throws JsonProcessingException{
        ProductDto productDto = mapper.readValue(json, ProductDto.class);
        Product product = productDtoMapper.productDtoToProduct(productDto);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doPut(@RequestBody String json) throws JsonProcessingException{
        ProductDto productDto = mapper.readValue(json, ProductDto.class);
        Product product = productDtoMapper.productDtoToProduct(productDto);
        productService.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> doDelete(@RequestParam("id") Integer id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

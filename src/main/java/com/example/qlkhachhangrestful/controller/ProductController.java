package com.example.qlkhachhangrestful.controller;


import com.example.qlkhachhangrestful.model.Product;
import com.example.qlkhachhangrestful.service.IProductIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductIService productIService;

    @GetMapping("")  // hien thi
    public ResponseEntity<Iterable<Product>> findAllProduct(){
        List<Product> products = productIService.findAll();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}") // tim kiem theo id
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productIService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PostMapping // them moi
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        productIService.save(product);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    @PutMapping("/{id}") // sua
    public ResponseEntity<Product> updateCustomer(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productIService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       product.setId(productOptional.get().getId());
        productIService.save(product);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("/{id}") // xoa
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> customerOptional = productIService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productIService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search-name")
    public ResponseEntity<Iterable<Product>> findAllByNameContaining(String name) {
        return new ResponseEntity<>(productIService.findAllByNameContaining(name), HttpStatus.OK);
    }


}

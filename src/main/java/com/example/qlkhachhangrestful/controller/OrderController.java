package com.example.qlkhachhangrestful.controller;

import com.example.qlkhachhangrestful.model.Orderr;
import com.example.qlkhachhangrestful.model.Product;
import com.example.qlkhachhangrestful.service.IOrderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderIService orderIService;
    @GetMapping
    public ResponseEntity<Iterable<Orderr>> findAllOrderr(){
        List<Orderr> orderrs = (List<Orderr>) orderIService.findAll();
        if(orderrs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderrs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orderr> findProductById(@PathVariable Long id) {
        Optional<Orderr> orderrtOptional = orderIService.findById(id);
        if (!orderrtOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderrtOptional.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> saveOrderr(@RequestBody Orderr orderr) {
        orderIService.save(orderr);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Orderr> updateCustomer(@PathVariable Long id, @RequestBody Orderr orderr) {
        Optional<Orderr> orderrOptional = orderIService.findById(id);
        if (!orderrOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderr.setId(orderrOptional.get().getId());
        orderIService.save(orderr);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Orderr> deleteOrder(@PathVariable Long id) {
        Optional<Orderr> orderrOptional = orderIService.findById(id);
        if (!orderrOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderIService.remove(id);
        return new ResponseEntity<>(orderrOptional.get(), HttpStatus.NO_CONTENT);
    }
}

package com.example.qlkhachhangrestful.controller;


import com.example.qlkhachhangrestful.model.OrderDetail;
import com.example.qlkhachhangrestful.model.Product;
import com.example.qlkhachhangrestful.service.IOrderDetailIService;
import com.example.qlkhachhangrestful.service.IProductIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/odrderdtails")
public class OrderDtailController {
    @Autowired
    private IOrderDetailIService orderDetailIService;
    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> findAllorderdetail(){
        List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailIService.findAll();
        if(orderDetails.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailIService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetailOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> saveOrderdetail(@RequestBody OrderDetail orderDetail) {
        orderDetailIService.save(orderDetail);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateOrderdetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOptional = orderDetailIService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setId(orderDetailOptional.get().getId());
        orderDetailIService.save(orderDetail);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailIService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailIService.remove(id);
        return new ResponseEntity<>(orderDetailOptional.get(), HttpStatus.NO_CONTENT);
    }
}

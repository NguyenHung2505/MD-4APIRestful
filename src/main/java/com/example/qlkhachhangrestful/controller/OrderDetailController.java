package com.example.qlkhachhangrestful.controller;
import com.example.qlkhachhangrestful.model.OrderDetail;
import com.example.qlkhachhangrestful.model.Orderr;
import com.example.qlkhachhangrestful.model.Product;
import com.example.qlkhachhangrestful.service.IOrderDetailIService;
import com.example.qlkhachhangrestful.service.IOrderIService;
import com.example.qlkhachhangrestful.service.IProductIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/ordersDetails/{id}")
public class OrderDetailController {
    @Autowired
//    OrderDetailService orderDetailService;
    IOrderDetailIService orderDetailIService;
    //    OrderService orderService;
    @Autowired
    private IOrderIService orderIService;
    //    ProductService productService;
    @Autowired
    private IProductIService productIService;
    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> findAllProduct() {
        List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailIService.findAll();
        if (orderDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<OrderDetail> create(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setOrderr(orderIService.findById(id).get());
        orderDetailIService.save(orderDetail);
        Product product = orderDetail.getProduct();
        product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
        productIService.save(product);
        Orderr orderr = orderDetail.getOrderr();
        long total = orderr.getAmount() + product.getPrice() * orderDetail.getQuantity();
        orderr.setAmount(total);
        orderIService.save(orderr);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }
    @DeleteMapping("/details/{idd}")
    public ResponseEntity<OrderDetail> delete(@PathVariable Long idd) {
        OrderDetail orderDetail = orderDetailIService.findById(idd).get();
        orderDetailIService.remove(idd);
        Product product = orderDetail.getProduct();
        product.setQuantity(product.getQuantity() + orderDetail.getQuantity());
        productIService.save(product);
        Orderr orderr = orderDetail.getOrderr();
        long total = orderr.getAmount() - product.getPrice() * orderDetail.getQuantity();
        orderr.setAmount(total);
        orderIService.save(orderr);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/details/{idd}")
    public ResponseEntity<OrderDetail> update(@PathVariable Long id, @PathVariable Long idd, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> oldOrderDetail = orderDetailIService.findById(idd);
        if (!oldOrderDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setId(oldOrderDetail.get().getId());
        orderDetail.setOrderr(oldOrderDetail.get().getOrderr());
        Product oldProduct = oldOrderDetail.get().getProduct();
        oldProduct.setQuantity(oldProduct.getQuantity() + oldOrderDetail.get().getQuantity());
        long oldMoney = oldProduct.getPrice() * oldOrderDetail.get().getQuantity();
        productIService.save(oldProduct);
        orderDetailIService.save(orderDetail);
        OrderDetail newOrderDetail = orderDetailIService.findById(orderDetail.getId()).get();
        Product newProduct = newOrderDetail.getProduct();
        newProduct.setQuantity(newProduct.getQuantity() - newOrderDetail.getQuantity());
        productIService.save(newProduct);
        Orderr newOrder = newOrderDetail.getOrderr();
        newOrder.setAmount(newOrder.getAmount() - oldMoney + newOrderDetail.getQuantity() * newProduct.getPrice());
        orderIService.save(newOrder);
        return new ResponseEntity<>(orderDetailIService.findById(orderDetail.getId()).get(), HttpStatus.OK);
    }
}
package com.example.qlkhachhangrestful.service.impl;
import com.example.qlkhachhangrestful.model.OrderDetail;
import com.example.qlkhachhangrestful.repository.IOrderDtailRepository;
import com.example.qlkhachhangrestful.service.IOrderDetailIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailService implements IOrderDetailIService {
@Autowired
    IOrderDtailRepository orderDtailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return orderDtailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDtailRepository.findById(id);
    }


    @Override
    public void save(OrderDetail orderDetail) {
        orderDtailRepository.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        orderDtailRepository.deleteById(id);
    }
}

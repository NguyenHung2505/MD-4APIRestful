package com.example.qlkhachhangrestful.service.impl;

import com.example.qlkhachhangrestful.model.Orderr;
import com.example.qlkhachhangrestful.repository.IOrderRepository;
import com.example.qlkhachhangrestful.service.IOrderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderIService {
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public List<Orderr> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orderr> findById(Long id) {
        return orderRepository.findById(id);
    }


    @Override
    public void save(Orderr order) {
        orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}

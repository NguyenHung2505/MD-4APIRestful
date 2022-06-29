package com.example.qlkhachhangrestful.repository;

import com.example.qlkhachhangrestful.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDtailRepository extends JpaRepository<OrderDetail, Long> {
}

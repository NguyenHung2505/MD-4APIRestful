package com.example.qlkhachhangrestful.repository;

import com.example.qlkhachhangrestful.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository <Orderr,Long> {
}

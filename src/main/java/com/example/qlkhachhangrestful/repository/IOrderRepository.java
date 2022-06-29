package com.example.qlkhachhangrestful.repository;

import com.example.qlkhachhangrestful.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository <Orderr,Long> {
}

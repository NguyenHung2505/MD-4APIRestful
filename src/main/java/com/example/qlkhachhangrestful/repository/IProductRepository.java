package com.example.qlkhachhangrestful.repository;

import com.example.qlkhachhangrestful.model.Product;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product , Long> {
}

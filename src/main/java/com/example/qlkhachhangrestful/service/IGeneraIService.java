package com.example.qlkhachhangrestful.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGeneraIService <H>{
    List<H> findAll();

    Optional<H> findById(Long id);

    void save(H h);

    void remove(Long id);
}

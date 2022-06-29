package com.example.qlkhachhangrestful.service;

import com.example.qlkhachhangrestful.model.Product;

public interface IProductIService extends IGeneraIService<Product>{
    Iterable<Product> findAllByNameContaining(String name);
}

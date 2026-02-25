package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Product;

public interface ProductService {
    abstract Product addProduct(Product product);
    abstract boolean deleteProduct(long id);
    abstract Product updateProduct(Product product,long id);
    abstract List<Product> getAllProducts();
}

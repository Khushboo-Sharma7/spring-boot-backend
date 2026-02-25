package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Product;
import com.example.springapp.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public boolean deleteProduct(long id) {
        if(productRepo.existsById(id))
        {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product updateProduct(Product product, long id) {
        Product p=productRepo.findById(id).orElse(null);
        if(p!=null)
        {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setDescription(product.getDescription());
            p.setQuantity(product.getQuantity());
            p.setImageUrl(product.getImageUrl());
            return productRepo.save(p);
        }
        return null;
    }

}

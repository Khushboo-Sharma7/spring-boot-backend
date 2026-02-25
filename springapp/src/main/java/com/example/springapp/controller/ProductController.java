package com.example.springapp.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springapp.model.Product;
import com.example.springapp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    
    @PostMapping(value="/admin/product/add",consumes = "multipart/form-data")
    public ResponseEntity<?> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("image") MultipartFile imageFile) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(productJson, Product.class);

            if (imageFile != null && !imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                product.setImageUrl(base64Image);
            }

            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.status(201).body(savedProduct);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



    @GetMapping("/product/getAll")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(!products.isEmpty())
        {
            return ResponseEntity.status(201).body(products);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping(value="/admin/product/{id}", consumes= {"multipart/form-data"})
public ResponseEntity<?> editProduct(
        @PathVariable long id,
        @RequestPart("product") String productJson, // receive as String
        @RequestPart(value = "image", required=false) MultipartFile imageFile) {

    try {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productJson, Product.class); // deserialize JSON

        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            product.setImageUrl(base64Image);
        }

        Product saveProduct = productService.updateProduct(product, id);
        return ResponseEntity.status(201).body(saveProduct);

    } catch (Exception e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}

    
    @DeleteMapping("/admin/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        if(productService.deleteProduct(id))
        {
            return ResponseEntity.status(201).body(true);
        }
        return ResponseEntity.status(400).body(false);
    }
}

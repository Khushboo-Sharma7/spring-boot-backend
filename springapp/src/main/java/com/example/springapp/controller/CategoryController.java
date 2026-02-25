package com.example.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Category;
import com.example.springapp.service.CategoryService;
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/category/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category c=categoryService.addCategory(category);
        if(c!=null)
        {
            return ResponseEntity.status(201).body(c);
        }
        return ResponseEntity.status(400).build();
    }
    @DeleteMapping("/admin/category/delete/{deleteId}")
    public ResponseEntity<?> deleteCategory(@PathVariable long deleteId) {
        boolean c=categoryService.deleteCategory(deleteId);
        if(c)
        {
            return ResponseEntity.status(201).body(c);
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/category/")
    public ResponseEntity<?> getallCategory() {
        List<Category> l=categoryService.getallCategory();
        if(l.isEmpty()){
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(l);
    }
    @PutMapping("/admin/category/edit/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category) {
        Category c=categoryService.updateCategory(category, id);
        if(c!=null)
        {
            return ResponseEntity.status(200).body(c);
        }
        return ResponseEntity.status(400).build();
    }
    
}

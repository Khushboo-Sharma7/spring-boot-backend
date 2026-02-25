package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Category;
import com.example.springapp.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public boolean deleteCategory(long id) {
        if(categoryRepo.existsById(id)){
            categoryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getallCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Category updateCategory(Category category,long id) {
        Category c=categoryRepo.findById(id).get();
        if(c!=null)
        {
            c.setName(category.getName());
            c.setDescription(category.getDescription());
            return categoryRepo.save(c);
        }
        return null;
    }

}

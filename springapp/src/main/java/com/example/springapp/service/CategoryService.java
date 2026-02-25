package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Category;


public interface CategoryService {
    abstract Category addCategory(Category category);
    abstract boolean deleteCategory(long id);
    abstract Category updateCategory(Category category,long id);
    abstract List<Category> getallCategory();
}

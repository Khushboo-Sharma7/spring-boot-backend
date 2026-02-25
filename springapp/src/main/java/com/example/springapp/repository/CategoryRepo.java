package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}

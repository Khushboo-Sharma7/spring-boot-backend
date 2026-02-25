package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.model.Order;


public interface OrderRepo extends JpaRepository<Order, Long>{
    Order findByUserId(long user_id);
}

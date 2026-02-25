package com.example.springapp.service;

import com.example.springapp.model.Order;

public interface OrderService {
    abstract Order placeOrder(long user_id);
    abstract Order updateOrder(String status,long id);
    abstract boolean deleteOrder(long id);
    abstract Order getOrderDetails(long id);
}

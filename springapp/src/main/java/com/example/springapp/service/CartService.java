package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Cart;
import com.example.springapp.model.CartItem;


public interface CartService {
    abstract Cart addToCart(long user_id,long product_id,int quantity);
    abstract boolean deleteFromCart(long user_id,long product_id);
    abstract Cart updateCart(long user_id,long product_id,int quantity);
    abstract List<CartItem> readCart(long user_id);
}

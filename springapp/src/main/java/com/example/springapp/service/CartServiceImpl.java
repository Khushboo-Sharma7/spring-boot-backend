package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Cart;
import com.example.springapp.model.CartItem;
import com.example.springapp.model.Product;
import com.example.springapp.model.User;
import com.example.springapp.repository.CartItemRepo;
import com.example.springapp.repository.CartRepo;
import com.example.springapp.repository.ProductRepo;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartitemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Cart addToCart(long user_id, long product_id, int quantity) {
        Cart cart = cartRepo.findByUserId(user_id);

        if(cart==null)
        {
            cart = new Cart();
            User user = new User();
            user.setId(user_id);
            cart.setUser(user);
            cartRepo.save(cart);
        }

        if(cartitemRepo.productExists(user_id, product_id))
        {
            CartItem cr = cartitemRepo.getCartProduct(user_id, product_id);
            cr.setQuantity(cr.getQuantity() + quantity);
            cartitemRepo.save(cr);
        }
        else
        {
            CartItem new_item = new CartItem();
            new_item.setCart(cart);
            Product p = productRepo.findById(product_id).orElse(null);
            new_item.setProduct(p);
            new_item.setQuantity(quantity);
            cartitemRepo.save(new_item);
        }

        return cartRepo.findById(cart.getId()).get();
    }

    @Override
    public boolean deleteFromCart(long user_id, long product_id) {
        Cart cart = cartRepo.findByUserId(user_id);
        if(cart!=null)
        {
            if(cartitemRepo.productExists(user_id, product_id))
            {
                CartItem cr = cartitemRepo.getCartProduct(user_id, product_id);
                cr.setQuantity(cr.getQuantity()-1);
                cartitemRepo.save(cr);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CartItem> readCart(long user_id) {
        Cart cart = cartRepo.findByUserId(user_id);
        if(cart != null)
        {
            return cartitemRepo.getCartProducts(user_id);
        }
        return null;
    }

    @Override
    public Cart updateCart(long user_id, long product_id, int quantity) {
        Cart cart = cartRepo.findByUserId(user_id);

        if(cart==null)
        {
            cart = new Cart();
            User user = new User();
            user.setId(user_id);
            cart.setUser(user);
            cartRepo.save(cart);
        }

        if(cartitemRepo.productExists(user_id, product_id))
        {
            CartItem cr = cartitemRepo.getCartProduct(user_id, product_id);
            cr.setQuantity(cr.getQuantity()+quantity);
            cartitemRepo.save(cr);
        }
        else
        {
            CartItem new_item = new CartItem();
            new_item.setCart(cart);
            Product p = productRepo.findById(product_id).orElse(null);
            new_item.setProduct(p);
            new_item.setQuantity(quantity);
            cartitemRepo.save(new_item);
        }

        return cartRepo.findById(cart.getId()).get();
    }

    
}

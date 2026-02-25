package com.example.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Cart;
import com.example.springapp.model.CartItem;
import com.example.springapp.service.CartService;

@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private CartService cartService;
     
    @PostMapping("add")
    public ResponseEntity<?> addToCart(@RequestParam long user_id, @RequestParam long product_id, @RequestParam int quantity)
    {
        Cart cart=cartService.addToCart(user_id, product_id, quantity);
        if(cart!=null)
        {
            return ResponseEntity.status(201).body(cart);
        }
        return ResponseEntity.status(400).build();
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<?> addToCart(@RequestParam long user_id, @RequestParam long product_id)
    {
        if(cartService.deleteFromCart(user_id, product_id)){
            return ResponseEntity.status(201).body(true);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("getall")
    public ResponseEntity<?> readCart(@RequestParam long user_id) {
        List<CartItem> products = cartService.readCart(user_id);
        if(!products.isEmpty())
        {
            return ResponseEntity.status(201).body(products);
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("edit")
    public ResponseEntity<?> updateCart(@RequestParam long user_id, @RequestParam long product_id, @RequestParam int quantity)
    {
        Cart cart=cartService.updateCart(user_id, product_id, quantity);
        if(cart!=null)
        {
            return ResponseEntity.status(201).body(cart);
        }
        return ResponseEntity.status(400).build();
    }
    
}

package com.example.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Order;
import com.example.springapp.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add/{user_id}")
    public ResponseEntity<?> placeOrder(@PathVariable("user_id") long user_id) {
        Order o = orderService.placeOrder(user_id);
        if(o!=null)
        {
            return ResponseEntity.status(201).body(o);
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/getOrder/{user_id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable long user_id) {
        Order o = orderService.getOrderDetails(user_id);
        if(o!=null)
        {
            return ResponseEntity.status(201).body(o);
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/deleteOrder/{user_id}")
    public ResponseEntity<?> deleteOrder(@PathVariable long user_id)
    {
        if(orderService.deleteOrder(user_id)){
            return ResponseEntity.status(201).body(true);
        }
        return ResponseEntity.status(400).body(false);
    }
    
    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> updateOrder(@PathVariable long user_id, @RequestBody String status) {
        Order o = orderService.updateOrder(status, user_id);
        if(o!=null)
        {
            return ResponseEntity.status(201).body(o);
        }
        return ResponseEntity.status(400).build();
    }
    
}

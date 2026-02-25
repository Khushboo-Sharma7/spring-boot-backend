package com.example.springapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.CartItem;
import com.example.springapp.model.Order;
import com.example.springapp.model.OrderItem;
import com.example.springapp.repository.CartItemRepo;
import com.example.springapp.repository.OrderRepo;
import com.example.springapp.repository.UserRepo;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartItemRepo cartitemRepo;

    @Autowired 
    private UserRepo userRepo;

    @Override
    public Order placeOrder(long user_id) {
        List<CartItem> products = cartitemRepo.getCartProducts(user_id);
        Order order = new Order();
        order.setUser(userRepo.findById(user_id).get());
        order.setOrderDate(new Date());
        order.setStatus("PLACED");
        double price = 0;
        List<OrderItem> orderList = order.getProducts();
        if(orderList == null)
        {
            orderList = new ArrayList<>();
            order.setProducts(orderList);
        }
        for(CartItem item:products)
        {
            OrderItem order_item = new OrderItem();
            order_item.setOrder(order);
            order_item.setProduct(item.getProduct());
            order_item.setQuantity(item.getQuantity());
            order_item.setPrice(item.getProduct().getPrice() * item.getQuantity());
            price+=order_item.getPrice();
            
            orderList.add(order_item);
        }
        order.setTotalAmount(price);

        return orderRepo.save(order);
    }

    @Override
    public boolean deleteOrder(long user_id) {
        Order o=orderRepo.findByUserId(user_id);
        if(o!=null)
        {
            if(o.getStatus().equalsIgnoreCase("placed"))
            {
                orderRepo.deleteById(o.getId());
                return true;
            }
            //add exception
        }
        return false;
    }

    @Override
    public Order updateOrder(String status, long user_id) {
        Order o=orderRepo.findByUserId(user_id);
        if(o!=null)
        {
            o.setStatus(status);
            return orderRepo.save(o);
        }
        return null;
    }

    @Override
    public Order getOrderDetails(long user_id) {
        Order o=orderRepo.findByUserId(user_id);
        if(o!=null)
        {
            return o;
        }
        return null;
    }
    
}

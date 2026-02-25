package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springapp.model.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long>{

    @Query("select count(c)>0 from CartItem c where c.cart.id=:c_id and c.product.id=:p_id")
    boolean productExists(@Param("c_id") long c_id, @Param("p_id") long p_id);

    @Query("select c from CartItem c where c.cart.id=:c_id and c.product.id=:p_id")
    CartItem getCartProduct(@Param("c_id") long c_id, @Param("p_id") long p_id);

    @Query("select c from CartItem c where c.cart.id=:c_id")
    List<CartItem> getCartProducts(@Param("c_id") long c_id);
}

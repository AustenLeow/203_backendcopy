package com.cs203g1t2.springjwt.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.Item;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    public List<Order> findByUser(User user);

    // public List<Order> findByOrderIsNotNull();

    // public Order findByUserAndOrder(User user, Order);
    
    // public CartItem findByUserAndOrder(User user, Order order);

    @Query("delete from Order o where o.user.id = ?1 and o.id = ?2")
    @Modifying
    public void deleteByUserAndOrder(Long user_id, Long order_id);
}


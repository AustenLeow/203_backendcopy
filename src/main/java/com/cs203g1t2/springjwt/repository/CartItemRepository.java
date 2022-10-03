package com.cs203g1t2.springjwt.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.Item;
import com.cs203g1t2.springjwt.models.User;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByUser(User user);

    public CartItem findByUserAndItem(User user, Item item);
}

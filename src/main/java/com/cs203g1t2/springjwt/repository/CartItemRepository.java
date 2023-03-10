package com.cs203g1t2.springjwt.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.Item;
import com.cs203g1t2.springjwt.models.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public List<CartItem> findByOrderIsNotNull();

    public List<CartItem> findByUserAndOrderIsNull(User user);

    public List<CartItem> findByUserAndOrderIsNotNull(User user);

    public CartItem findByUserAndItemAndOrderIsNull(User user, Item item);
    
    @Query("update CartItem c set c.quantity = ?1 where c.item.id = ?2 and c.user.id = ?3 and c.order is null")
    @Modifying
    public void updateQuantity(Integer quantity, Long item_id, Long user_id);

    @Query("delete from CartItem c where c.user.id = ?1 and c.item.id = ?2 and c.order is null")
    @Modifying
    public void deleteByUserAndItem(Long user_id, Long item_id);

    @Query("delete from CartItem c where c.user.id = ?1 and c.order.id = ?2")
    @Modifying
    public void deleteByUserAndOrder(Long user_id, Long order_id);

    @Query("delete from CartItem c where c.user.id = ?1")
    @Modifying
    public void deleteByUser(Long user_id);
}


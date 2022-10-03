package com.cs203g1t2.springjwt.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs203g1t2.springjwt.repository.CartItemRepository;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.Item;

import com.cs203g1t2.springjwt.repository.ItemRepository;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ItemRepository itemRepo;

    public List<CartItem> listCartItems(User user) {
        return cartRepo.findByUser(user);
    }

    public Integer addItem(Long id, Integer quantity, User user) {
        Integer addedQuantity = quantity;

        Item item = itemRepo.findById(id).get();

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
            cartItem.setItem(item);
        }

        cartRepo.save(cartItem);

        return addedQuantity;
    }
}

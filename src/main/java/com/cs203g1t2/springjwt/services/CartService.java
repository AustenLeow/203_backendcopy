package com.cs203g1t2.springjwt.services;

import java.math.BigDecimal;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import com.cs203g1t2.springjwt.repository.CartItemRepository;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.Item;
import com.cs203g1t2.springjwt.repository.ItemRepository;
import com.cs203g1t2.springjwt.exceptions.NotEnoughItemsInStockException;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ItemRepository itemRepo;

    //get cart
    public List<CartItem> listCartItems(User user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);
        return cartItems;
    }

    public BigDecimal getTotalPrice(User user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice.add(cartItem.getSubtotal());
        }
        return totalPrice;
    }

    // add an item to cart
    public Integer addItem(Long id, User user) throws NotEnoughItemsInStockException {
        Integer addedQuantity = 1;

        Item item = itemRepo.findById(id).get();

        CartItem cartItem = cartRepo.findByUserAndItem(user, item);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + 1;
            if (addedQuantity > item.getQuantity()) {
                throw new NotEnoughItemsInStockException(item);
            }
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setUser(user);
            cartItem.setItem(item);
        }

        cartRepo.save(cartItem);
        return addedQuantity;
    }

    // edit quantity of items and recalculate subtotal
    public BigDecimal updateQuantity(Long itemid, Integer quantity, User user) throws NotEnoughItemsInStockException{
        Item item = itemRepo.findById(itemid).get();
        if (item.getQuantity() < quantity) {
            throw new NotEnoughItemsInStockException(item);
        }
        
        cartRepo.updateQuantity(quantity, itemid, user.getId());
        
        BigDecimal subtotal = item.getPrice().multiply(new BigDecimal(quantity));
        return subtotal;
    }

    // delete the item in the user's cart
    public void removeItem(Long itemid, User user) {
        cartRepo.deleteByUserAndItem(user.getId(), itemid);
    }

    public void deleteCart(User user) {
        cartRepo.deleteByUser(user.getId());
    }
}

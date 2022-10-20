package com.cs203g1t2.springjwt.services;

import java.math.BigDecimal;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs203g1t2.springjwt.services.CartService;
import com.cs203g1t2.springjwt.repository.CartItemRepository;
import com.cs203g1t2.springjwt.repository.OrderRepository;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.Item;
import com.cs203g1t2.springjwt.models.Order;
import com.cs203g1t2.springjwt.repository.ItemRepository;
import com.cs203g1t2.springjwt.exceptions.NotEnoughItemsInStockException;

@Service
@Transactional
public class OrderService {
    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartService cartService;

    // get order history
    public List<Order> listOrders(User user) {
        List<Order> orders = orderRepo.findByUser(user);
        return orders;
    }

    // make an order
    public void addOrder(User user) {
        
        List<CartItem> cartItems = cartService.listCartItems(user);

        Order order = new Order();
        order.setCartItems(cartItems);
        order.setUser(user);
        order.setTotal(getTotalPrice(user));

        orderRepo.save(order);
        
    }

    public BigDecimal getTotalPrice(User user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice.add(cartItem.getSubtotal());
        }
        return totalPrice;
    }
}

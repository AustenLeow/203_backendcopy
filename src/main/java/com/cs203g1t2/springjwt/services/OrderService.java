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
        // for (Order order : orders) {
        //     order.getCartItems();
        // }
        return orders;
    }

    // make an order
    public void addOrder(User user) {
        
        // List<CartItem> cartItems = cartService.listCartItems(user);
        List<CartItem> cartItems = cartRepo.findByUserAndOrderIsNull(user);

        Order order = new Order();
        order.setCartItems(getCartItems(user));
        order.setUser(user);
        order.setTotal(getTotalPrice(user));
        order.setCarbonTotal(getTotalCarbon(user));

        orderRepo.save(order); 
    }

        // make a donated order
        public void addDonatedOrder(User user) {
        
            // List<CartItem> cartItems = cartService.listCartItems(user);
            List<CartItem> cartItems = cartRepo.findByUserAndOrderIsNull(user);
    
            Order order = new Order();
            order.setCartItems(getCartItems(user));
            order.setUser(user);
            order.setTotal(getTotalPrice(user));
            order.setCarbonTotal(getTotalCarbon(user));
            order.setDonated(true);
    
            orderRepo.save(order); 
        }

    // delete an order
    // public void deleteOrder(Long order_id, User user) {
    //     cartRepo.deleteByUserAndOrder(user.getId(), order_id);
    //     orderRepo.deleteByUserAndOrder(user.getId(), order_id);
    // }

    public List<CartItem> getCartItems(User user) {
        List<CartItem> cartItems = cartRepo.findByUserAndOrderIsNull(user);
        return cartItems;
    }

    public BigDecimal getTotalPrice(User user) {
        List<CartItem> cartItems = cartRepo.findByUserAndOrderIsNull(user);
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice.add(cartItem.getSubtotal());
        }
        return totalPrice;
    }

    public BigDecimal getTotalCarbon(User user) {
        List<CartItem> cartItems = cartRepo.findByUserAndOrderIsNull(user);
        BigDecimal totalCarbon = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            totalCarbon = totalCarbon.add(cartItem.getCarbontotal());
        }
        return totalCarbon;
    }

    public BigDecimal getAllCarbonSaved() {
        List<CartItem> orders = cartRepo.findByOrderIsNotNull();
        BigDecimal cs = new BigDecimal(0);

        for (CartItem o : orders) {
            cs = cs.add(o.getCarbontotal());
        }
        return cs;
    }

    public void collected(User user, Long ordered) {
        Order order = orderRepo.findByUserAndId(user, ordered);
        order.setCollected(true); 
        orderRepo.save(order);
    }
}

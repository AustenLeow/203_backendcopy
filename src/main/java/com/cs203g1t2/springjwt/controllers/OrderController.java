package com.cs203g1t2.springjwt.controllers;

import java.math.BigDecimal;
import java.util.*;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.*;

import com.cs203g1t2.springjwt.services.OrderService;
import com.cs203g1t2.springjwt.services.CartService;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.models.Order;
import com.cs203g1t2.springjwt.controllers.AuthController;
import com.cs203g1t2.springjwt.repository.ItemRepository;
import com.cs203g1t2.springjwt.exceptions.NotEnoughItemsInStockException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthController userController;

    @Autowired
    private CartService cartService;

    @GetMapping("/order")
    public List<Order> listOrders(@AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);

        List<Order> orders = orderService.listOrders(user);
        return orders;
    }

    @GetMapping("/ordertotalcarbon")
    public BigDecimal getAllCarbonSaved() {
        return orderService.getAllCarbonSaved();
    }

    // @GetMapping("/order/{id}")
    // public List<CartItem> getOrderSummary(@PathVariable("id") Long id, @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
    //     authentication = SecurityContextHolder.getContext().getAuthentication();
    //     User user = userController.getLoggedInUser(authentication);

    //     List<CartItem> orderItems = cartService.listCartItems(user);
    //     return orderItems;
    // }

    @PostMapping("/order/add")
    public String addOrder(@AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);

        orderService.addOrder(user);
        return "Order confirmed!";
    }

    @PostMapping("/order/donate")
    public String addDonatedOrder(@AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);

        orderService.addDonatedOrder(user);
        return "Order confirmed!";
    }

    @PostMapping("/order/{id}/collected")
    public void collected(@AuthenticationPrincipal org.springframework.security.core.Authentication authentication, @PathVariable("id") Long orderid) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);

        orderService.collected(user,orderid);
        // return "Order confirmed!";
    }

    // @DeleteMapping("/order/delete/{id}")
    // public String deleteOrder(@PathVariable("id") Long orderid, @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {

    //     authentication = SecurityContextHolder.getContext().getAuthentication();
    //     User user = userController.getLoggedInUser(authentication);
  
    //     orderService.deleteOrder(orderid, user);
    //     return "Order cancelled!";
    // }
}

package com.cs203g1t2.springjwt.controllers;

import java.util.*;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/order/add")
    public String addOrder(@AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);

        orderService.addOrder(user);
        return "Order confirmed!";
    }
}

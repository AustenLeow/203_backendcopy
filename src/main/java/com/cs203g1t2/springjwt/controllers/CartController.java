package com.cs203g1t2.springjwt.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;

import com.cs203g1t2.springjwt.services.CartService;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.security.services.UserDetailsImpl;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.controllers.AuthController;

@Controller
public class CartController {
    
    @Autowired
    private CartService cartService;

    @Autowired
    private AuthController userController;

    @GetMapping("/cart")
    public List<CartItem> getCart(Model model, @AuthenticationPrincipal Authentication authentication) {
        User user = userController.getLoggedInUser(authentication);
        List<CartItem> cartItems = cartService.listCartItems(user);
        return cartItems;
    }
}

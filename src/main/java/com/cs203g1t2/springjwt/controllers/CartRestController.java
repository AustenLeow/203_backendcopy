package com.cs203g1t2.springjwt.controllers;

import java.util.*;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cs203g1t2.springjwt.services.CartService;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.controllers.AuthController;

@RestController
public class CartRestController {
    @Autowired
    private CartService cartService;

    @Autowired
    private AuthController userController;

    @PostMapping("/cart/add/{id}/{quantity}")
    public String addItemToCart(@PathVariable("id") Long itemid, @PathVariable("quantity") Integer quantity, @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        // if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        //     return "You have to log in first!";
        // }
        User user = userController.getLoggedInUser(authentication);
        if (user == null) {
            return "You have to log in first!";
        }
        Integer addedQuantity = cartService.addItem(itemid, quantity, user);
        return addedQuantity + " items of this product were added to your shopping cart.";
    }
}

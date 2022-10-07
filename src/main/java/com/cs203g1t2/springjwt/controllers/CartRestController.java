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

import com.cs203g1t2.springjwt.services.CartService;
import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.models.CartItem;
import com.cs203g1t2.springjwt.controllers.AuthController;
import com.cs203g1t2.springjwt.repository.ItemRepository;

@RestController
@RequestMapping("/api/v1")
public class CartRestController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private AuthController userController;

    @PostMapping("/cart/add/{id}")
    public String addItemToCart(@PathVariable("id") Long itemid,
        @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        // if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        //     return "You have to log in first!";
        // }
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);
        // if (user == null) {
        //     return "You have to log in first!";
        // }
        Integer addedQuantity = cartService.addItem(itemid, user);
        return itemRepo.findById(itemid).get().getItemName() + " was added to your shopping cart, which now has " + addedQuantity + " of this item.";
    }

    @PutMapping("/cart/update/{id}/{quantity}")
    public String updateQuantity(@PathVariable("id") Long itemid, @PathVariable("quantity") Integer quantity, 
        @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        // if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        //     return "You have to log in first!";
        // }
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);
        // if (user == null) {
        //     return "You have to log in first!";
        // }
        BigDecimal subTotal = cartService.updateQuantity(itemid, quantity, user);
        return "Your cart now has " + quantity + " " + itemRepo.findById(itemid).get().getItemName();
    }

    @DeleteMapping("/cart/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long itemid, 
        @AuthenticationPrincipal org.springframework.security.core.Authentication authentication) {
        // if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        //     return "You have to log in first!";
        // }
        authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userController.getLoggedInUser(authentication);
        // if (user == null) {
        //     return "You have to log in first!";
        // }
        cartService.removeItem(itemid, user);
        return itemRepo.findById(itemid).get().getItemName() + " has been removed from your cart.";
    }
}

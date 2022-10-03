package com.cs203g1t2.springjwt.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cs203g1t2.springjwt.repository.*;
import com.cs203g1t2.springjwt.models.*;
import java.util.Optional;
import lombok.*;
import com.cs203g1t2.springjwt.security.jwt.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    ItemRepository itemRepository;


    @GetMapping("/items")
    public List<Item> getItem() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new RuntimeException("Unable to find item with id" + id);
        }

        return item.get();
    }
    

    @PostMapping("/items")
    public Item addItem(@Valid @RequestBody Item newItem){
        Item item = new Item();
        item.setItemName(newItem.getItemName());
        item.setPrice(newItem.getPrice());
        item.setBrand(newItem.getBrand());
        item.setDescription(newItem.getDescription());
        item.setExpiry_date(newItem.getExpiry_date());
        item.setType(newItem.getType());
        return itemRepository.save(item);
    }

    @DeleteMapping(path = "/items/{Id}")
    public void deleteItemById(
            @PathVariable("Id") Long id) {
        if (itemRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Item with id of " + id + " does not exist");
        }
        itemRepository.deleteById(id);
        if (id == null)
            throw new RuntimeException();
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable Long id,
            @RequestBody Item newItem) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item with id of " + id + " does not exist");
        }
        if (newItem == null) {
            throw new RuntimeException("Item details Empty");
        }

        return itemRepository.findById(id).map(item -> {
            item.setItemName(newItem.getItemName());
            item.setPrice(newItem.getPrice());
            item.setBrand(newItem.getBrand());
            item.setDescription(newItem.getDescription());
            item.setExpiry_date(newItem.getExpiry_date());
            item.setType(newItem.getType());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException());
    }

}

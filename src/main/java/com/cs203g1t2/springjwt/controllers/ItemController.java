package com.cs203g1t2.springjwt.controllers;

import java.util.List;
import java.util.ArrayList;
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
import com.cs203g1t2.springjwt.exceptions.ItemExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin
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
        if (!(item.isPresent())) {
            throw new RuntimeException("Unable to find item with id" + id);
        }
        return item.get();
    }


    @GetMapping("/items/location/{location}")
    public List<Item> getItemByLocation(@PathVariable String location) {
        List<Item> items = itemRepository.findAll();
        List<Item> ret = new ArrayList<Item>();
        for ( Object item : items){
            Item theitem = (Item)item;
            if (theitem.getLocation().equals(location)){
                ret.add(theitem);
            }
        }
        return ret;
    }

    @GetMapping("/items/type/{type}")
    public List<Item> getItemByType(@PathVariable String type) {
        List<Item> items = itemRepository.findAll();
        List<Item> ret = new ArrayList<Item>();
        for ( Object item : items){
            Item theitem = (Item)item;
            if (theitem.getType().equals(type)){
                ret.add(theitem);
            }
        }
        return ret;
    }

    @GetMapping("/items/type+location/{type}/{location}")
    public List<Item> getItemByType(@PathVariable String type, @PathVariable String location) {
        List<Item> items = itemRepository.findAll();
        List<Item> ret = new ArrayList<Item>();
        for ( Object item : items){
            Item theitem = (Item)item;
            if (theitem.getType().equals(type) && theitem.getLocation().equals(location)){
                ret.add(theitem);
            }
        }
        return ret;
    }
    

    @PostMapping("/items/add")
    // @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public Item addItem(@Valid @RequestBody Item newItem) {
        if (itemRepository.existsByItemName(newItem.getItemName())
                && itemRepository.existsByBrand(newItem.getBrand())) {
            throw new ItemExistsException(newItem);
        }
        Item item = new Item();
        item.setItemName(newItem.getItemName());
        item.setPrice(newItem.getPrice());
        item.setBrand(newItem.getBrand());
        item.setDescription(newItem.getDescription());
        item.setExpiry_date(newItem.getExpiry_date());
        item.setType(newItem.getType());
        item.setQuantity(newItem.getQuantity());
        item.setUrl(newItem.getUrl());
        item.setCarbon(newItem.getCarbon());
        item.setLocation(newItem.getLocation());
        
        // Item item = new Item(
        // newItem.getItemName(),
        // newItem.getPrice(),
        // newItem.getBrand(),
        // newItem.getDescription(),
        // newItem.getExpiry_date(),
        // newItem.getType());
        
        return itemRepository.save(item);
    }

    @DeleteMapping(path = "/items/{Id}")
    // @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteItemById(
            @PathVariable("Id") Long id) {
        if (!(itemRepository.findById(id).isPresent())) {
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
        if (itemRepository.existsByItemName(newItem.getItemName())
                && itemRepository.existsByBrand(newItem.getBrand())) {
            throw new ItemExistsException(newItem);
        }

        if ( newItem.getQuantity() == 0){
            deleteItemById(itemRepository.findById(id).get().getId());
            throw new RuntimeException("Item has no more stock");
        }
        return itemRepository.findById(id).map(item -> {
            item.setItemName(newItem.getItemName());
            item.setPrice(newItem.getPrice());
            item.setBrand(newItem.getBrand());
            item.setDescription(newItem.getDescription());
            item.setExpiry_date(newItem.getExpiry_date());
            item.setType(newItem.getType());
            item.setQuantity(newItem.getQuantity());
            item.setUrl(newItem.getUrl());
            item.setCarbon(newItem.getCarbon());
            item.setLocation(newItem.getLocation());
            return itemRepository.save(item);

        }).orElseThrow(() -> new RuntimeException());
    }
}

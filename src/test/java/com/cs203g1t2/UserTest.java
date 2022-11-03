package com.cs203g1t2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.meta.When;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs203g1t2.springjwt.repository.*;
import com.cs203g1t2.springjwt.controllers.*;
import com.cs203g1t2.springjwt.models.*;
import java.math.BigDecimal;
import com.cs203g1t2.springjwt.payload.request.*;
import com.cs203g1t2.springjwt.payload.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository users;

    @InjectMocks
    private UserController UserController;

    
    private RoleRepository roles;

    @InjectMocks
    private AuthController authController;

    @Autowired
    PasswordEncoder encoder;

    // @Test
    // void findUser_UserName_ReturnUser(){

    // User user = new User(
    // "aus9",
    // "aus9@gmail.com",
    // "password",
    // 0L,
    // 0L,
    // "prata"
    // );

    // when((users.findById(user.id)))

    // }

    // List<Item> sameItems = new ArrayList<Item>();
    // Boolean response = true;
    // when((items.existsByItemName(any(String.class)))
    // && items.existsByBrand(any(String.class))).thenReturn(response);
    // when(items.save(any(Item.class))).thenReturn(item);

    // Item savedItem = itemController.addItem(item);

    @Test
    void resigteruser_NewUsername_ReturnSaveduser() {

        // Set<String> r = new HashSet<String>();
        // r.add("USER");

        // SignupRequest s = new SignupRequest("aus10",
        // "aus10@gmail.com", "password", 
        // r,
        // "prata");

        // String password = encoder.encode("password");
      
        User user = new User(
                "aus1",
                "aus1@gmail.com",
                "password",
                1L,
                1L,
                "prata");
        // when(users.existsByUsername(any(String.class))).thenReturn(false);

        // when(users.existsByEmail(any(String.class))).thenReturn(false);
        // Set<Role> roles = new HashSet<>();
        // roles.add(any(Role.class));
        // when(roles.setRoles(roles)).thenReturn(any(Role.class));
        when(users.save(any(User.class))).thenReturn(user);

        User savedUser =  users.save(user);

        assertNotNull(savedUser);
        // verify(users).existsByUsername(user.getUsername());
        // verify(users).existsByEmail(user.getEmail());
        verify(users).save(user);
        // when(roles.findByName(any(String.class))).thenReturn(any(Boolean.class));
    }

    // @PostMapping("/signup")
    // public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest
    // signUpRequest) {
    // if (userRepository.existsByUsername(signUpRequest.getUsername())) {
    // return ResponseEntity
    // .badRequest()
    // .body(new MessageResponse("Error: Username is already taken!"));
    // }

    // if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    // return ResponseEntity
    // .badRequest()
    // .body(new MessageResponse("Error: Email is already in use!"));
    // }
    // @Test
    // void updateUser_NotFound_ReturnNull() {
    //     User user = new User(
    //             "aus1",
    //             "aus1@gmail.com",
    //             "password",
    //             0L,
    //             0L,
    //             "prata");

    //     Long userid = 1L;
        
    //     when((users.existsByUsername(any(String.class)))
    //     && users.existsByEmail(any(String.class))).thenReturn(false);

    //     when(users.findById(user.getId())).thenReturn(Optional.empty());
    //     // when(users.save(any(User.class))).thenReturn(user);
    //     // assertException
    //     User updatedUser = UserController.updateUser(userid, user);

    //     assertNull(updatedUser);
    //     verify(users).existsByUsername(user.getUsername());
    //     verify(users).existsByEmail(user.getEmail());
    // }
}







// @Test
//     void addItem_NewName_ReturnSavedItem() {

//         Item item =new Item(
//             "Wintermelon",
//         BigDecimal.valueOf(2),
//         "finest",
//         "juicy",
//         "21/12/2022",
//         "fruit",
//         Long.valueOf(10),
//         "https://images.heb.com/is/image/HEBGrocery/000320934",
//         BigDecimal.valueOf(0.5),
//         "east");
//         List<Item> sameItems = new ArrayList<Item>();
//         Boolean response = true;
//         when((items.existsByItemName(any(String.class)))
//         && items.existsByBrand(any(String.class))).thenReturn(response);
//         when(items.save(any(Item.class))).thenReturn(item);

//         Item savedItem = itemController.addItem(item);

//         // assert ***
//         assertNotNull(savedItem);
//         verify(items).existsByItemName(item.getItemName());
//         verify(items).existsByBrand(item.getBrand());
//         verify(items).save(item);
//     }

//     @Test
//     void addItem_SameTitle_ReturnNull() {
//         // your code here
//         Item item = new Item("Watermelon",
//                 BigDecimal.valueOf(2),
//                 "fairprice",
//                 "juicy",
//                 "21/12/2022",
//                 "fruit",
//                 Long.valueOf(10),
//                 "https://images.heb.com/is/image/HEBGrocery/000320934",
//                 BigDecimal.valueOf(0.5),
//                 "east");
//         ArrayList<Item> sameItem = new ArrayList<Item>();
//         sameItem.add((new Item("Watermelon",
//                 BigDecimal.valueOf(2),
//                 "fairprice",
//                 "juicy",
//                 "21/12/2022",
//                 "fruit",
//                 Long.valueOf(10),
//                 "https://images.heb.com/is/image/HEBGrocery/000320934",
//                 BigDecimal.valueOf(0.5),
//                 "east")));

//         // when(items.findById(item.getId())).thenReturn(Optional.of(sameItem.get(Math.toIntExact(((items.findById(item.getId()))).get().getId()))));
//         Boolean response = true;
//         when((items.existsByItemName(any(String.class)))
//         && items.existsByBrand(any(String.class))).thenReturn(response);
//         Item saveditem = itemController.addItem(item);
        
//         assertNull(saveditem);
//         verify(items).existsByItemName(item.getItemName());
//         verify(items).existsByBrand(item.getBrand());
//         verify(items).save(item);
//     }

//     @Test
//     void updateItem_NotFound_ReturnNull() {
//         Item item = new Item("Watermelon",
//                 BigDecimal.valueOf(2),
//                 "fairprice",
//                 "juicy",
//                 "21/12/2022",
//                 "fruit",
//                 Long.valueOf(10),
//                 "https://images.heb.com/is/image/HEBGrocery/000320934",
//                 BigDecimal.valueOf(0.5),
//                 "east");

//         Long itemId = 113L;
//         Boolean response = false;
//         // when ( items.existsById(item.getId())).thenReturn(response);

//         Boolean response2 = true;
//         when((items.existsByItemName(any(String.class)))
//         && items.existsByBrand(any(String.class))).thenReturn(response2);

//         // when(item.getQuantity()).thenReturn(any(Long.class));

//         when(items.findById(itemId)).thenReturn(Optional.empty());

//         // when(items.save(any(Item.class))).thenReturn(item);

//         Item updatedItem = itemController.updateItem(itemId, item);

//         assertNull(updatedItem);
//         // verify(items).existsById(updatedItem.getId());
//         verify(items).existsByItemName(item.getItemName());
//         verify(items).existsByBrand(item.getBrand());
//         // verify(items).save(item);
//     }
// package com.cs203g1t2;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import com.cs203g1t2.springjwt.repository.*;
// import com.cs203g1t2.springjwt.controllers.*;
// import com.cs203g1t2.springjwt.exceptions.NotEnoughItemsInStockException;
// import com.cs203g1t2.springjwt.services.*;
// import com.cs203g1t2.springjwt.models.*;
// import java.math.BigDecimal;

// @ExtendWith(MockitoExtension.class)
// public class CartTest {
//     @Mock
//     CartItemRepository cartRepo;

//     @InjectMocks
//     CartService cartService;

//     @Mock
//     private ItemRepository items;

//     @InjectMocks
//     private ItemController itemController;

//     @Test
//     void addCart_NewCartItem_ReturnSavedCartItem() throws NotEnoughItemsInStockException {

//         // mock the user
//         User user = new User(
//             "aus",
//             "email@email.com",
//             "notpassword",
//             2L,
//             2L,
//             "hello"
//         );

//         assertNotNull(user);

//         // mock the item
//         Item item = new Item(
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
//         // List<Item> sameItems = new ArrayList<Item>();
//         Boolean response = true;
//         when((items.existsByItemName(any(String.class)))
//         && items.existsByBrand(any(String.class))).thenReturn(response);
//         when(items.save(any(Item.class))).thenReturn(item);

//         Item savedItem = itemController.addItem(item);

//         assertNotNull(savedItem);
//         verify(items).existsByItemName(item.getItemName());
//         verify(items).existsByBrand(item.getBrand());
//         verify(items).save(item);

//         // arrange ***
//         CartItem cartitem = new CartItem(1, user, item);

//         // mock the cartRepo findByUserAndItemAndOrderIsNull method
//         when((cartRepo.findByUserAndItemAndOrderIsNull(any(User.class), any(Item.class)))).thenReturn(new CartItem());

//         // mock the "save" operation 
//         when(cartRepo.save(any(CartItem.class))).thenReturn(cartitem);

//         // act ***
//         Integer addedQuantity = cartService.addItem(item.getId(),user);
        
//         // assert ***
//         assertNotNull(cartitem);
//         verify(cartRepo.findByUserAndItemAndOrderIsNull(user, item));
//         verify(cartRepo).save(cartitem);
//     }
// }

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cs203g1t2.springjwt.repository.*;
import com.cs203g1t2.springjwt.controllers.*;
import com.cs203g1t2.springjwt.models.*;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ItemsTest {

    @Mock
    private ItemRepository items;

    @InjectMocks
    private ItemController itemController;

    @Test
    void addItem_NewName_ReturnSavedItem() {

        Item item =new Item(
            "Wintermelon",
        BigDecimal.valueOf(2),
        "finest",
        "juicy",
        "21/12/2022",
        "fruit",
        Long.valueOf(10),
        "https://images.heb.com/is/image/HEBGrocery/000320934",
        BigDecimal.valueOf(0.5),
        "east");
        List<Item> sameItems = new ArrayList<Item>();
        Boolean response = true;
        when((items.existsByItemName(any(String.class)))
        && items.existsByBrand(any(String.class))).thenReturn(response);
        when(items.save(any(Item.class))).thenReturn(item);

        Item savedItem = itemController.addItem(item);

        // assert ***
        assertNotNull(savedItem);
        verify(items).existsByItemName(item.getItemName());
        verify(items).existsByBrand(item.getBrand());
        verify(items).save(item);
    }

    @Test
    void addItem_SameTitle_ReturnNull() {
        // your code here
        Item item = new Item("Watermelon",
                BigDecimal.valueOf(2),
                "fairprice",
                "juicy",
                "21/12/2022",
                "fruit",
                Long.valueOf(10),
                "https://images.heb.com/is/image/HEBGrocery/000320934",
                BigDecimal.valueOf(0.5),
                "east");
        ArrayList<Item> sameItem = new ArrayList<Item>();
        sameItem.add((new Item("Watermelon",
                BigDecimal.valueOf(2),
                "fairprice",
                "juicy",
                "21/12/2022",
                "fruit",
                Long.valueOf(10),
                "https://images.heb.com/is/image/HEBGrocery/000320934",
                BigDecimal.valueOf(0.5),
                "east")));

        // when(items.findById(item.getId())).thenReturn(Optional.of(sameItem.get(Math.toIntExact(((items.findById(item.getId()))).get().getId()))));
        when(items.findByid(item.getId())).thenReturn(sameItem);
        Item saveditem = itemController.addItem(item);
        assertNull(saveditem);
        verify(items).findByid(item.getId());
    }

    @Test
    void updateItem_NotFound_ReturnNull() {
        Item item = new Item("Watermelon",
                BigDecimal.valueOf(2),
                "fairprice",
                "juicy",
                "21/12/2022",
                "fruit",
                Long.valueOf(10),
                "https://images.heb.com/is/image/HEBGrocery/000320934",
                BigDecimal.valueOf(0.5),
                "east");

        Long itemId = 115L;
        when(items.findById(itemId)).thenReturn(Optional.empty());

        Item updatedItem = itemController.updateItem(itemId, item);

        assertNull(updatedItem);
        verify(items).findById(itemId);
    }
}

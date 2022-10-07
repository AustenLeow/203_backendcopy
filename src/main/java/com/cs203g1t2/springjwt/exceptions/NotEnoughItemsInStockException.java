package com.cs203g1t2.springjwt.exceptions;

import com.cs203g1t2.springjwt.models.Item;

public class NotEnoughItemsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough items in stock";

    public NotEnoughItemsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughItemsInStockException(Item item) {
        super(String.format("Not enough %s items in stock. Only %d left", item.getItemName(), item.getQuantity()));
    }

}

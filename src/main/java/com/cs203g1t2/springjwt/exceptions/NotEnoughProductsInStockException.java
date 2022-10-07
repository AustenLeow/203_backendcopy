package com.cs203g1t2.springjwt.exceptions;

import com.cs203g1t2.springjwt.models.Item;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Item item) {
        super(String.format("Not enough %s products in stock. Only %d left", item.getItemName(), item.getQuantity()));
    }

}

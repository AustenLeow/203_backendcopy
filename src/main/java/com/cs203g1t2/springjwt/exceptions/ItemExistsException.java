package com.cs203g1t2.springjwt.exceptions;

import com.cs203g1t2.springjwt.models.Item;

public class ItemExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  
    public ItemExistsException(Item newItem) {
      super(String.format("Item, %s, already exists, please edit it in your listing instead", newItem.getItemName()));
    }
  }

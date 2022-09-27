package com.cs203g1t2.springjwt.dto.item;

import lombok.Data;

import java.util.List;

@Data
public class ItemSearchRequest {
    private List<String> brands;
    private List<Integer> prices;
    private Boolean sortByPrice;
    private String brand;
}

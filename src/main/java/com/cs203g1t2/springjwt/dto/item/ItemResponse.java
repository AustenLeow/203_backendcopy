package com.cs203g1t2.springjwt.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
    private Long id;
    private String itemName;
    private String brand;
    private Integer price;
    private String filename;
}

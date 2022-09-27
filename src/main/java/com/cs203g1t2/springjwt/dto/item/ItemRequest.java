package com.cs203g1t2.springjwt.dto.item;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemRequest {

    private Long id;
    private String filename;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String itemName;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 255)
    private String brand;

    @NotNull(message = "Fill in the input field")
    private Integer price;

}

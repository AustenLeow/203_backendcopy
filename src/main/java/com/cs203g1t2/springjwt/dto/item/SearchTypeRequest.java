package com.cs203g1t2.springjwt.dto.item;

import lombok.Data;
import com.cs203g1t2.springjwt.enums.*;

@Data
public class SearchTypeRequest {
    private SearchItem searchType;
    private String text;
}

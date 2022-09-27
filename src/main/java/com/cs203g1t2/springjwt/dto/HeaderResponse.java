package com.cs203g1t2.springjwt.dto;

import java.util.List;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeaderResponse<T> {
    private List<T> items;
    private HttpHeaders headers;
}

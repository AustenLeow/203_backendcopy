package com.cs203g1t2.springjwt.repository.projection;


public interface ItemProjection {
    Long getId();
    String getItemName();
    String getBrand();
    Integer getPrice();
    
    void setBrand(String brand);
    void setPrice(Integer price);
}

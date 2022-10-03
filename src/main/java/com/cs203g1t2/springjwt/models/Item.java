package com.cs203g1t2.springjwt.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {


    // public Item(String itemName, Integer price, String brand,  String description, String expiry_date, String type) {
    //     this.itemName = itemName;
    //     this.price = price;
    //     this.brand = brand;
    //     this.description = description;
    //     this.expiry_date = expiry_date;
    //     this.type = type;
    // }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "expiry_date")
    private String expiry_date;

    @Column(name = "type")
    private String type;
    
    // @Column(name = "filename")
    // private String filename;

    // @OneToMany
    // @ToString.Exclude
    // private List<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

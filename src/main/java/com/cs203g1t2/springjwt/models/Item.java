package com.cs203g1t2.springjwt.models;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
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

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Column(name = "expiry_date")
    private String expiry_date;

    @Column(name = "type")
    private String type;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "url")
    private String url;
    
    // @Column(name = "filename")
    // private String filename;

    @ManyToMany(mappedBy = "order")
    private Set<Order> orders;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemListing> listings;

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

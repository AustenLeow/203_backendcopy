package com.cs203g1t2.springjwt.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    // @Column(name = "post_index")
    // private Integer postIndex;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToMany
    @JoinTable(name="order_items",
        joinColumns = 
            @JoinColumn(name="order_id"),
        inverseJoinColumns =
            @JoinColumn(name="item_id"))
    private Set<Item> items;

    public Order() {
        this.date = LocalDate.now();
        this.orderItems = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

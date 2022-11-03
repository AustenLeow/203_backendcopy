package com.cs203g1t2.springjwt.models;

import java.math.BigDecimal;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.util.*;

import com.cs203g1t2.springjwt.services.OrderService;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<CartItem> cartItems;

    @Column(name = "total_price")
    private BigDecimal total;

    @Column(name = "total_carbon")
    private BigDecimal carbonTotal;

    @Column(name = "is_collected")
    private Boolean collected = false;

    @Column(name = "is_donated")
    private Boolean donated = false;

    public Order() {

    }
}

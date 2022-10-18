package com.cs203g1t2.springjwt.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // @ManyToOne
    // @JsonIgnore
    // @JoinColumn(name = "order_id")
    // private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem orderItem = (CartItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Transient
    public BigDecimal getSubtotal() {
        return this.item.getPrice().multiply(new BigDecimal(quantity));
    }

    @Transient
    public BigDecimal getCarbontotal() {
        return this.item.getCarbon().multiply(new BigDecimal(quantity));
    }
    
}

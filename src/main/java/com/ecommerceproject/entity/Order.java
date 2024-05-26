package com.ecommerceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "orders")
@Data

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @OneToOne(mappedBy = "order")
    private Review review;
}

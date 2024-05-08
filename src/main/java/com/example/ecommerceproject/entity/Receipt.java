package com.example.ecommerceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt")
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "receipt")
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "shipping_info_id", nullable = false)
    private ShippingInfo shippingInfo;
}

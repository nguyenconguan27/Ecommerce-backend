package com.example.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shipping_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false)
    private String number;

    @OneToMany(mappedBy = "shippingInfo", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Receipt> receipt = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JoinColumn(name = "address", nullable = false)
    private String address;
}

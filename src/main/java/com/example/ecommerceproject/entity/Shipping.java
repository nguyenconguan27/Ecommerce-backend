package com.example.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "shipping")
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shipping_date", nullable = false)
    private Date shippingDate;

    @Column(name = "receive_date")
    private Date receiveDate;

    @OneToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;
}

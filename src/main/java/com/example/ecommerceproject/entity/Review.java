package com.example.ecommerceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.sql.Date;

@Entity
@Table(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    @Lob
    @Nationalized
    private String content;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "comment_date", nullable = false)
    private Date commentDate;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;
}

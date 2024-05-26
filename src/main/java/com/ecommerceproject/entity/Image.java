package com.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Image {

    @Id
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}

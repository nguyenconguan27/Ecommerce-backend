package com.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "product_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSize {
    @EmbeddedId
    private ProductSizeKey id;

    @ManyToOne
    @MapsId("productId")
    private Product product;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}

package com.ecommerceproject.dwentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_dim")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductDim {
    @Id
    private Integer product_id;

    @Column(name = "title")
    private String title;
}

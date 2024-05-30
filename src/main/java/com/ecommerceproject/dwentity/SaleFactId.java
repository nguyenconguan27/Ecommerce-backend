package com.ecommerceproject.dwentity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleFactId implements Serializable {
    @Column(name = "product_id")
    private int product_id;
    @Column(name="time_id")
    private int time_id;


}
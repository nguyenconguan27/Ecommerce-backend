package com.ecommerceproject.dwentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_fact")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SaleFact {
    @EmbeddedId
    private SaleFactId id;

    private int revenue;
    private int totalSale;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductDim productDim;

    @ManyToOne
    @MapsId("time_id")
    @JoinColumn(name = "time_id")
    private TimeDim timeDim;

}

package com.ecommerceproject.dwentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_fact")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserFact {

    @Id
    private int time_id;

    private int totalRegister;

    @ManyToOne
    @JoinColumn(name = "time_id", insertable = false, updatable = false)
    private TimeDim timeDim;

}

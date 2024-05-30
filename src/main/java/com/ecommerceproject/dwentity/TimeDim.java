package com.ecommerceproject.dwentity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_dim")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TimeDim {

    @Id
    private Integer time_id;
    private Integer month;
    private Integer quater;
    private Integer year;
}

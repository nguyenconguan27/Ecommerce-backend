package com.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee extends User {
    @Column(name = "salary", nullable = false)
    private Integer salary;

    @JoinColumn(name = "address", nullable = false)
    private String address;
}

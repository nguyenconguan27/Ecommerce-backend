package com.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user1")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "number", nullable = false, length = 50)
    private String number;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Notification> notificationList = new ArrayList<>();
}

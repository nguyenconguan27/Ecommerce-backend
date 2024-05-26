package com.ecommerceproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")

public class Notification {
    @Id
    private Integer Id;
    private Date date;
    private Integer status;
    @Lob
    @Nationalized
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

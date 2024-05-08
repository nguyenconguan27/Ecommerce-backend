package com.example.ecommerceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 250)
    @Nationalized
    private String title;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "expense", nullable = false)
    private Integer expense;

    @Column(name = "pre_price")
    private Integer prePrice;

    @Column(name = "sold", nullable = false)
    private Integer sold;

    @Column(name="des", nullable = false)
    @Lob
    @Nationalized
    private String des;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "release_date", nullable = false)
    @DateTimeFormat
    private Date releaseDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductSize> productSizeList = new ArrayList<>();
}

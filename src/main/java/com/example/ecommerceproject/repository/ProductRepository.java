package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from product where product.price >= :min_price and price <= :max_price and product.title like %:title% and product.category_id = :category_id", nativeQuery = true)
    Page<Product> findByALlOptions(@Param("min_price") Integer minPrice, @Param("max_price") Integer maxPrice, @Param("category_id") Integer categoryId, @Param("title") String title, Pageable pageable);
    @Query(value = "select * from product where product.price >= :min_price and price <= :max_price and product.title like %:title%", nativeQuery = true)
    Page<Product> findByNoneCate(@Param("min_price") Integer minPrice, @Param("max_price") Integer maxPrice, @Param("title") String title, Pageable pageable);
}

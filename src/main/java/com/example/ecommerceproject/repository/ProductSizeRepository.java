package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.entity.ProductSize;
import com.example.ecommerceproject.entity.ProductSizeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeKey> {
}

package com.ecommerceproject.repository;

import com.ecommerceproject.entity.ProductSize;
import com.ecommerceproject.entity.ProductSizeKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeKey> {
}

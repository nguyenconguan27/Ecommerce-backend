package com.ecommerceproject.dwrepository;

import com.ecommerceproject.dwentity.ProductDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDimRepository extends JpaRepository<ProductDim, Integer> {
}

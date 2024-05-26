package com.ecommerceproject.repository;

import com.ecommerceproject.entity.Shipping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
}

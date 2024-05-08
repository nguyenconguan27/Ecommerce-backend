package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {
    List<ShippingInfo> findByCustomer_id(Integer customerId);
}

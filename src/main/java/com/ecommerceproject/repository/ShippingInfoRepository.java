package com.ecommerceproject.repository;

import com.ecommerceproject.entity.ShippingInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Integer> {
    List<ShippingInfo> findByCustomer_id(Integer customerId);
}

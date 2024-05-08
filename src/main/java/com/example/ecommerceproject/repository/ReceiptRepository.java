package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

    @Query("select r from Receipt r where r.status = :status and r.customer.id = :customerId")
    List<Receipt> findByStatusAndCustomer_id(@Param("status") Integer status, @Param("customerId") Integer customerId);
}

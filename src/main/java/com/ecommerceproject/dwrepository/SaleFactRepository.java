package com.ecommerceproject.dwrepository;

import com.ecommerceproject.dwentity.SaleFact;
import com.ecommerceproject.dwentity.SaleFactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleFactRepository extends JpaRepository<SaleFact, SaleFactId> {
    @Query(value = "select sum(sale_fact.revenue) from sale_fact where time_id = :timeId", nativeQuery = true)
    Integer findByRevenue(@Param("timeId") Integer timeId);
    @Query(value = "select sum(sale_fact.total_sale) from sale_fact where time_id = :timeId", nativeQuery = true)
    Integer findByTotal_Sale(@Param("timeId") Integer timeId);
}
package com.example.ecommerceproject.repository;

import com.example.ecommerceproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o " +
            "where o.cart.id = :cartId and o.status = :status")
    List<Order> findByCart_idAndStatus(@Param("cartId") Integer cartId, @Param("status") Integer status);
}

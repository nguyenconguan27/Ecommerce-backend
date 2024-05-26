package com.ecommerceproject.repository;

import com.ecommerceproject.entity.Review;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r join  r.order ord " +
            "join ord.product p " +
            "where p.id = :productId")
    List<Review> getByProductId(@Param("productId") Integer productId);

    @Query("select avg(r.rate) from Review r join  r.order ord " +
            "join ord.product p " +
            "where p.id = :productId")
    Double getAverageRate(@Param("productId")Integer productId);


}

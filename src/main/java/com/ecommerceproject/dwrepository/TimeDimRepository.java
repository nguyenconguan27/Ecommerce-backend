package com.ecommerceproject.dwrepository;

import com.ecommerceproject.dwentity.TimeDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeDimRepository extends JpaRepository<TimeDim, Integer> {
    @Query(value = "select o from TimeDim o where o.month = :month1 and o.year = :year1")
    List<TimeDim> findByMonthAndYear(@Param("month1") Integer month,@Param("year1") Integer year);
}

package com.ecommerceproject.dwrepository;

import com.ecommerceproject.dwentity.UserFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFactRepository extends JpaRepository<UserFact, Integer> {
}

package com.ecommerceproject.repository;

import com.ecommerceproject.entity.Notification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser_id(Integer userId);
}

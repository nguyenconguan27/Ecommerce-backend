package com.ecommerceproject.service.impl;

import com.ecommerceproject.dto.NotificationDTO;
import com.ecommerceproject.service.NotifyService;
import com.ecommerceproject.converter.NotificationConverter;
import com.ecommerceproject.entity.Notification;
import com.ecommerceproject.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationConverter notificationConverter;
    @Override
    public List<NotificationDTO> getByUser(Integer userId) {
        List<Notification> notificationList = notificationRepository.findByUser_id(userId);
        return notificationList.stream().map(notification -> notificationConverter.toDTO(notification)).collect(Collectors.toList());
    }
}

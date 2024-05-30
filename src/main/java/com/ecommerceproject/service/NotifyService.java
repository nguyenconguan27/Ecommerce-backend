package com.ecommerceproject.service;

import com.ecommerceproject.dto.NotificationDTO;

import java.util.List;

public interface NotifyService {
    List<NotificationDTO> getByUser(Integer userId);

}

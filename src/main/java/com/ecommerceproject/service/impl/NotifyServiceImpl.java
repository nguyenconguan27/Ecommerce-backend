package com.ecommerceproject.service.impl;

import com.ecommerceproject.dto.NotificationDTO;
import com.ecommerceproject.service.NotifyService;
import com.ecommerceproject.converter.NotificationConverter;
import com.ecommerceproject.entity.Notification;
import com.ecommerceproject.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate dateBefore30Days = currentDate.minusDays(30);
        for(int i = notificationList.size() - 1; i >= 0; i--) {
            LocalDate otherDate = notificationList.get(i).getDate().toLocalDate();
            if(otherDate.isBefore(dateBefore30Days)) {
                notificationRepository.delete(notificationList.get(i));
            }
            else {
                notificationDTOList.add(notificationConverter.toDTO(notificationList.get(i)));
            }
        }
        return notificationDTOList;
    }
}

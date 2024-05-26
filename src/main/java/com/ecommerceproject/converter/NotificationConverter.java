package com.ecommerceproject.converter;

import com.ecommerceproject.dto.NotificationDTO;
import com.ecommerceproject.entity.Notification;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class NotificationConverter {

    private final ModelMapper modelMapper;

    public NotificationDTO toDTO(Notification notification) {
        NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
        return notificationDTO;
    }
}

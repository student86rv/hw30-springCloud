package com.epam.springcloud.notification;

import com.epam.springcloud.notification.model.Notification;
import com.epam.springcloud.notification.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class NotificationController {

    private final Set<Notification> notifications = new HashSet<>();

    @PostMapping
    public ResponseEntity notify(@RequestBody UserDto user) {

        Notification notification = new Notification(user.getName());
        notifications.add(notification);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }
}

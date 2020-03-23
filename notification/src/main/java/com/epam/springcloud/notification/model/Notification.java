package com.epam.springcloud.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Notification {

    private String user;
    private final Notifier notifyBy = Notifier.EMAIL;

    enum Notifier {
        EMAIL
    }
}

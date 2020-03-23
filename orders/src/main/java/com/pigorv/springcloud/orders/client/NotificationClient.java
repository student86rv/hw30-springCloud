package com.pigorv.springcloud.orders.client;

import com.pigorv.springcloud.orders.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications")
public interface NotificationClient {

    @PostMapping
    ResponseEntity addNotification(@RequestBody UserDto user);
}

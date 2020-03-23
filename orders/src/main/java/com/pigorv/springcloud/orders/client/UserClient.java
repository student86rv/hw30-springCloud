package com.pigorv.springcloud.orders.client;

import com.pigorv.springcloud.orders.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UserClient {

    @GetMapping("/{name}")
    UserDto getUserByName(@PathVariable("name") String name);
}

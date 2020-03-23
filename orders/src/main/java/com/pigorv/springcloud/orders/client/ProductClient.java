package com.pigorv.springcloud.orders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "products")
public interface ProductClient {

    @PutMapping("/{name}")
    ResponseEntity removeProductByName(@PathVariable("name") String name);
}

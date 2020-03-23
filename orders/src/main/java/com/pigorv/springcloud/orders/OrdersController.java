package com.pigorv.springcloud.orders;

import java.util.ArrayList;
import java.util.List;

import com.pigorv.springcloud.orders.client.NotificationClient;
import com.pigorv.springcloud.orders.client.ProductClient;
import com.pigorv.springcloud.orders.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
public class OrdersController {
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    NotificationClient notificationClient;
    @Autowired
    ProductClient productClient;
    @Autowired
    UserClient userClient;

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        UserDto userDto = userClient.getUserByName(order.getUserName());

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productClient.removeProductByName(order.getProduct());
        notificationClient.addNotification(userDto);

        orderList.add(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }
}

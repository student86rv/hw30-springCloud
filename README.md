# Spring Cloud homework

## Add the project to your repository with changes written below.

### Notification server
* Run service at `localhost:8484`
* Notification service should be registered in Eureka Server with `notifications` service id
* Add route to API Gateway. `localhost:8080/notifications/**` should be routed to `notfication` service running at port `8484` 
* Add Spring MVC annotations and some logic to `NotificationController.class` to handle GET and POST request.
    * POST - adds Notification for given user to list  
    * GET - returns collection of notifications  

### Order service
* Rewrite `OrdersController.createNewOrder()` to use `FeignClient's` instead of `RestTeamplate`.
* Add POST HTTP call in `OrdersController.createNewOrder()` to `notification` service using `FeignClient`

## Order of manual testing:
* Run all services (eureka-server is first)
* Create user 
``` bash
curl -X POST http://localhost:8080/users
Response should look like: {"name": "XXXXxxxx"}
```
* Create product 
``` bash
curl -X POST http://localhost:8080/products
Response should look like: { "name": "YYYYyyyy", "quantity": 2}
```
* Create order 
``` bash
curl --url http://localhost:8080/orders \
     -H "Content-Type: application/json" \
     -d '{"userName": "XXXXxxxx", "product": "YYYYyyyy"}'
```
* Check is `notification` service have handled POST request
``` bash
curl -X GET http://localhost:8080/notifications
Response should look like: [{"user":"XXXXxxxx","notifyBy":"EMAIL"}]%  
```
 * Call `notification` service directly without API Gateway should responds with the same result.
``` bash
curl -X GET http://localhost:8484/
Response should look like: [{"user":"XXXXxxxx","notifyBy":"EMAIL"}]%  
```

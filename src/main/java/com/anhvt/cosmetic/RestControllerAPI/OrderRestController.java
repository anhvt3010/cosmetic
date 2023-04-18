package com.anhvt.cosmetic.RestControllerAPI;


import com.anhvt.cosmetic.Entity.Order;
import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Service.OrderService;
import com.anhvt.cosmetic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")
public class OrderRestController {
    private final OrderService orderService;
    private final UserService userService;
    @Autowired
    public OrderRestController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<Iterable<Order>> getAll(){
        Iterable<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/user-id={id}")
    public ResponseEntity<Iterable<Order>> getByUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            Iterable<Order> orders = orderService.findByUser(id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

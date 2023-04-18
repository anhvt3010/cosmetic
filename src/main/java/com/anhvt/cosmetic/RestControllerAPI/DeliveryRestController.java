package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.Delivery;
import com.anhvt.cosmetic.Entity.User;
import com.anhvt.cosmetic.Service.DeliveryService;
import com.anhvt.cosmetic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/deliveries")
public class DeliveryRestController {
    private final DeliveryService deliveryService;
    private final UserService userService;
    @Autowired
    public DeliveryRestController(DeliveryService deliveryService, UserService userService) {
        this.deliveryService = deliveryService;
        this.userService = userService;
    }

    //Get All Delivery Address
    @GetMapping
    public ResponseEntity<Iterable<Delivery>> getAll(){
        Iterable<Delivery> deliveries = deliveryService.findAll();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    //Get Delivery by User
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Delivery>> getByUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()){
            Iterable<Delivery> deliveries = deliveryService.findByUser(id);
            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

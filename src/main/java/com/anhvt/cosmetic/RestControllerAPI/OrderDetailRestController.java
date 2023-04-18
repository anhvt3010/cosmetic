package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.OrderDetail;
import com.anhvt.cosmetic.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order-details")
public class OrderDetailRestController {
    private final OrderDetailService orderDetailService;
    @Autowired
    public OrderDetailRestController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //Get All order detail
    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> getAll(){
        Iterable<OrderDetail> orderDetails = orderDetailService.findAll();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}

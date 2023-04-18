package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.OrderDetail;
import com.anhvt.cosmetic.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService{
    private final OrderDetailRepository orderDetailRepository ;
    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public Iterable<OrderDetail> findAll(){
        return orderDetailRepository.findAll();
    }


}
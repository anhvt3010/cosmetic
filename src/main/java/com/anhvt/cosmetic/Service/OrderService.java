package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Order;
import com.anhvt.cosmetic.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IService<Order>{
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        orderRepository.save(order);
        return order;
    }

    public Iterable<Order> findByUser(Long id){
        return orderRepository.findByUser(id);
    }

    public Optional<Order> findByID(Long id){
//        return convertToOrderDTO(orderRepository.findById(id).get()));
        return orderRepository.findById(id);
    }


}

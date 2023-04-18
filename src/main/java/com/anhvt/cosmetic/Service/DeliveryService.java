package com.anhvt.cosmetic.Service;


import com.anhvt.cosmetic.Entity.Delivery;
import com.anhvt.cosmetic.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Iterable<Delivery> findAll(){
        return deliveryRepository.findAll();
    }

    public Iterable<Delivery> findByUser(Long id){
        return deliveryRepository.findByUser(id);
    }

    public void save(Delivery delivery){
        deliveryRepository.save(delivery);
    }
}

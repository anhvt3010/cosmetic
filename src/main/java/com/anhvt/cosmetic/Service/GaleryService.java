package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Galery;
import com.anhvt.cosmetic.Repository.GaleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GaleryService {
    private final GaleryRepository galeryRepository;
    @Autowired
    public GaleryService(GaleryRepository galeryRepository) {
        this.galeryRepository = galeryRepository;
    }

    public Iterable<Galery> findAll(){
        return galeryRepository.findAll();
    }

    public Iterable<Galery> findByProduct(Long id){
        return galeryRepository.findByProduct(id);
    }

    public Optional<Galery> findById(Long id){
        return galeryRepository.findById(id);
    }

    public void save(Galery galery){
        galeryRepository.save(galery);
    }
}

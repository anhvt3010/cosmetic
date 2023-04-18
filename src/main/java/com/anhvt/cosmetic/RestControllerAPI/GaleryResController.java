package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.Galery;
import com.anhvt.cosmetic.Entity.Product;
import com.anhvt.cosmetic.Repository.ProductRepository;
import com.anhvt.cosmetic.Service.GaleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/galeries")
public class GaleryResController {
    private final GaleryService galeryService;
    private final ProductRepository productRepository;

    @Autowired
    public GaleryResController(GaleryService galeryService,
                               ProductRepository productRepository) {
        this.galeryService = galeryService;
        this.productRepository = productRepository;
    }

    //Get All image
    @GetMapping
    public ResponseEntity<Iterable<Galery>> getAll(){
        Iterable<Galery> galeries = galeryService.findAll();
        return new ResponseEntity<>(galeries, HttpStatus.OK);
    }

    //Get image by product
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Galery>> getByProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Iterable<Galery> galeries = galeryService.findByProduct(id);
            return new ResponseEntity<>(galeries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Galery> getById(@PathVariable Long id){
        Optional<Galery> optionalGalery = galeryService.findById(id);
        return optionalGalery.map(galery -> new ResponseEntity<>(galery, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

package com.anhvt.cosmetic.RestControllerAPI;

import com.anhvt.cosmetic.Entity.Cart;
import com.anhvt.cosmetic.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/carts")
public class CartRestcontroller {
    private final CartService cartService;
    @Autowired
    public CartRestcontroller(CartService cartService) {
        this.cartService = cartService;
    }

    //get All product in cart of user
    @GetMapping
    public ResponseEntity<Iterable<Cart>> getAll(){
        Iterable<Cart> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

}

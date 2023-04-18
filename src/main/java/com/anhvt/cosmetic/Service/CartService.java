package com.anhvt.cosmetic.Service;
import com.anhvt.cosmetic.Entity.Cart;
import com.anhvt.cosmetic.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Iterable<Cart> findAll(){
        return cartRepository.findAll();
    }
    public void save(Cart cart){
        cartRepository.save(cart);
    }


}

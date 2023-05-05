package com.anhvt.cosmetic.Model;

import com.anhvt.cosmetic.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    private Product product;
    private int quantity;

}

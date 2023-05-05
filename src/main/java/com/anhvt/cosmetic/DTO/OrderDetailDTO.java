package com.anhvt.cosmetic.DTO;

import com.anhvt.cosmetic.Entity.Order;
import com.anhvt.cosmetic.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private long id;
    private Order order;
    private Product product;
    private int quantity;
    private float price;
}

package com.anhvt.cosmetic.Model;

import com.anhvt.cosmetic.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private User user;
    private String orderDate;
    private String deliveryDate;
    private String note;
    private List<OrderDetailRequest> orderDetails;
}

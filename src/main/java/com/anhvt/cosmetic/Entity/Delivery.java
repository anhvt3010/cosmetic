package com.anhvt.cosmetic.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cus_name")
    private String cus_name;

    @Column(name = "cus_phone")
    private String cus_phone;

    @Column(name = "address")
    private String address;
}

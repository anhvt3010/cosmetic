package com.anhvt.cosmetic.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @Column(name="id")
    @NotBlank(message="not blank")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="order_date")
    @NotBlank(message = "not blank")
    private String order_date;

    @Column(name="delivery_date")
    private String delivery_date;

    @Column(name="note")
    private String note;

    @Column(name="status")
    private Byte status;

}

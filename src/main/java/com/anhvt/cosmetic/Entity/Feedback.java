package com.anhvt.cosmetic.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="feedback")
public class Feedback {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="content")
    private String content;
}

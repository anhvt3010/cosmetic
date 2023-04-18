package com.anhvt.cosmetic.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="parent_id")
    private long parent_id;

    @Column(name="cat_name")
    private String name;

    @Column(name="status")
    private Byte status;

//    @OneToMany(mappedBy = "category")
//    private Set<Product> productSet;
}

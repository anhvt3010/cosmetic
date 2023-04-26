package com.anhvt.cosmetic.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", insertable = false, referencedColumnName = "id")
    private Category category;

    @Column(name="name", length = 100)
//    @NotBlank(message = "Not blank")
    private String name;

    @Column(name = "price")
//    @NotBlank(message = "Not blank")
    private float price;

    @Column(name="quantity")
//    @NotBlank(message = "Not blank")
    private int quantity;

    @Column(name="description", length = 500)
//    @NotBlank(message = "Not blank")
    private String description;

    @Column(name="image")
//    @NotBlank(message = "Not blank")
    private String image;

    @Column(name="created_at",length = 10)
    private String created_at;

    @Column(name="status")
    private Byte status;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Galery> galeryList;

}

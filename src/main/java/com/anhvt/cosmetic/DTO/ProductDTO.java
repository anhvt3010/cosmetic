package com.anhvt.cosmetic.DTO;

import com.anhvt.cosmetic.Entity.Category;
import com.anhvt.cosmetic.Entity.Galery;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private Category category;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private String image;
    private String created_at;
    private Byte status;
    private List<Galery> galeryList;
}

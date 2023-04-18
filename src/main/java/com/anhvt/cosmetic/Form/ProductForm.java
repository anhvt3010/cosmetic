package com.anhvt.cosmetic.Form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private long id;
//    private Category category;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private MultipartFile image;
    private String created_at;
    private Byte status;
}

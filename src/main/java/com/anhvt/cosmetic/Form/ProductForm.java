package com.anhvt.cosmetic.Form;

import com.anhvt.cosmetic.Entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private long id;
    private Category category;
    private String name;
    private float price;
    private int quantity;
    private String description;
    private MultipartFile image;
    private List<MultipartFile> thumbnail;
    private String created_at;
    private Byte status;
}

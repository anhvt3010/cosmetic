package com.anhvt.cosmetic.DTO;

import com.anhvt.cosmetic.Entity.Blog;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO implements Serializable {
    private long id;
    private String title;
    private String content;
    private String image;
    private String created_at;
    private Byte status;
}

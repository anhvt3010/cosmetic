package com.anhvt.cosmetic.Form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogForm {
    private long id;
    private String title;
    private String content;
    private MultipartFile image;
    private String created_at;
    private Byte status;
}

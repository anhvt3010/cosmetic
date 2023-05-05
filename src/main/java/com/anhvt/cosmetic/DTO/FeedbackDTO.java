package com.anhvt.cosmetic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String content;
    private String created_at;
}

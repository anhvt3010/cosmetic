package com.anhvt.cosmetic.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String fullname;
    private String username;
    private String email;
    private String phone;
    private String created_at;
    private Byte status;
}

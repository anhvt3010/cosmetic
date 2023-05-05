package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.BlogDTO;
import com.anhvt.cosmetic.DTO.UserDTO;
import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.Entity.User;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Utils.ConvertDate.TimestampToDate;
import static com.anhvt.cosmetic.Utils.HidePI.replaceEmail;
import static com.anhvt.cosmetic.Utils.HidePI.replacePhoneNumber;

public class UserMapper {
    public static UserDTO convertToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setFullname(user.getFullname());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhone(replacePhoneNumber(user.getPhone()));
        userDTO.setEmail(replaceEmail(user.getEmail()));
        userDTO.setCreated_at(TimestampToDate(user.getCreated_at()));
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }

    public static Iterable<UserDTO> convertToUserDTOs(Iterable<User> users) {
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserMapper::convertToUserDTO)
                .collect(Collectors.toList());
    }
}

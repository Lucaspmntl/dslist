package com.lucas.dslist.dto.user;

import com.lucas.dslist.models.User;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String nickname;
    private String description;

    public UserResponseDTO (User user){
        BeanUtils.copyProperties(user, this);
    }
}

package com.lucas.dslist.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {

    @Size(min = 4, max = 20, message = "O Nickname deve conter entre 4 e 20 caracteres.")
    private String nickname;

    @Size(min = 4, max = 25, message = "O primeiro nome deve conter entre 4 e 25 caracteres.")
    private String firstName;

    @Size(min = 4, max = 25, message = "O último nome deve conter entre 4 e 25 caracteres.")
    private String lastName;

    @Size(min = 10, max = 100, message = "O nome completo deve conter entre 10 e 100 caracteres.")
    private String fullName;

    @Email(message = "Erro de formatação no email, verifique o campo e tente novamente.")
    private String email;

    @Size(max = 255, message = "O limite para a descrição é de 255 caracteres.")
    private String description;

}

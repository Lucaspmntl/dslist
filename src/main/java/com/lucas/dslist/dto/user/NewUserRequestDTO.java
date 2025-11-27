package com.lucas.dslist.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserRequestDTO {

    @Size(min = 4, max = 20, message = "O Nickname deve conter entre 4 e 20 caracteres.")
    @NotBlank
    private String nickname;

    @Size(min = 4, max = 25, message = "O primeiro nome deve conter entre 4 e 25 caracteres.")
    @NotBlank
    private String firstName;

    @Size(min = 4, max = 25, message = "O último nome deve conter entre 4 e 25 caracteres.")
    @NotBlank
    private String lastName;

    @Size(min = 10, max = 100, message = "O nome completo deve conter entre 10 e 100 caracteres.")
    private String fullName;

    @Email(message = "Erro de formatação no email, verifique o campo e tente novamente.")
    @NotBlank
    private String email;

    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    @NotBlank
    private String password;
}

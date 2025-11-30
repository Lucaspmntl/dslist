package com.lucas.dslist.dto.game;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewGameRequestDTO {

    @Max(value = 2025, message = "Ano inválido, máximo: 2025.")
    @NotNull
    private Integer year;

    @Size(max = 25, min = 3, message = "Título inválido, deve conter entre 3 e 25 caracteres.")
    @NotBlank
    private String title;

    @Size(max = 25, message = "Gênero inválido, deve conter no máximo 25 caracteres.")
    @NotBlank
    private String genre;

    @NotBlank
    private String platforms;

    @Max(value = 10, message = "O valor máximo do score deve ser 10.")
    @Positive(message = "O valor de score deve ser maior do que zero.")
    @NotNull
    private Double score;

    @NotBlank
    private String imgUrl;

    @Size(max = 50, min = 10, message = "A descrição curta deve conter entre 10 e 50 caracteres.")
    @NotBlank
    private String shortDescription;

    @Size(max = 200, min = 10, message = "A descrição longa deve conter entre 10 e 200 caracteres.")
    private String longDescription;

}

package com.lucas.dslist.dto.game;

import com.lucas.dslist.models.Game;
import com.lucas.dslist.projections.GameMinProjection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameMinDTO {

    private Long id;

    @Max(value = 2025, message = "Ano inválido, máximo: 2025.")
    private Integer year;

    @Size(max = 25, min = 3, message = "Título inválido, deve conter entre 3 e 25 caracteres.")
    @NotBlank(message = "O título deve ser preenchido.")
    private String title;

    @Max(value = 10, message = "O valor máximo do score deve ser 10.")
    @NotBlank(message = "O score deve ser preenchido.")
    private Double score;

    @NotBlank
    private String imgUrl;

    @Size(max = 50, min = 10, message = "A descrição curta deve conter entre 10 e 50 caracteres.")
    @NotBlank
    private String shortDescription;

    public GameMinDTO(GameMinProjection projection){
        id = projection.getId();
        year = projection.getGameYear();
        imgUrl = projection.getImgUrl();
        title = projection.getTitle();
        shortDescription = projection.getShortDescription();
    }
}

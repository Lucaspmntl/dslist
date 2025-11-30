package com.lucas.dslist.dto.game;

import com.lucas.dslist.models.Game;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGameRequestDTO {

    @Max(value = 2025, message = "Ano inválido, máximo: 2025.")
    private Integer year;

    @Size(max = 25, min = 3, message = "Título inválido, deve conter entre 3 e 25 caracteres.")
    private String title;

    @Size(max = 25, message = "Gênero inválido, deve conter no máximo 25 caracteres.")
    private String genre;

    private String platforms;

    @Max(value = 10, message = "O valor máximo do score deve ser 10.")
    @Positive(message = "O valor de score deve ser maior do que zero.")
    private Double score;

    private String imgUrl;

    @Size(max = 50, min = 10, message = "A descrição curta deve conter entre 10 e 50 caracteres.")
    private String shortDescription;

    @Size(max = 200, min = 10, message = "A descrição longa deve conter entre 10 e 200 caracteres.")
    private String LongDescription;

    public UpdateGameRequestDTO (Game entity){
        BeanUtils.copyProperties(entity, this);
    }

}

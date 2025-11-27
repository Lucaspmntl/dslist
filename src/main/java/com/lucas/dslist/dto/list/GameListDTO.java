package com.lucas.dslist.dto.list;

import com.lucas.dslist.models.GameList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameListDTO {

    private Long id;
    private String name;
    private String description;

    public GameListDTO(GameList gameList){
        id = gameList.getId();
        name = gameList.getName();
    }
}

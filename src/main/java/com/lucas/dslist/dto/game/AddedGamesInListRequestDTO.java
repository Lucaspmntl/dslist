package com.lucas.dslist.dto.game;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddedGamesInListRequestDTO {

    @Positive
    private Long listId;

    @Positive
    private List<Long> gamesId;
    // private Long userId;
}

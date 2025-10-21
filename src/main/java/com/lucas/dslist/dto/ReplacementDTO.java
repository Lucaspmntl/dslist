package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;

public class ReplacementDTO {

    private Integer position;
    private Long gameId;
    private Long listId;

    public ReplacementDTO(){}

    public ReplacementDTO(Integer position, Long gameId, Long listId) {
        this.position = position;
        this.gameId = gameId;
        this.listId = listId;
    }

    public Integer getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getListId() {
        return listId;
    }
}

package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import org.antlr.v4.runtime.misc.NotNull;

public class ReplacementDTO {

    private Long sourcePosition;
    private Long targetPosition;
    private Long listId;

    public ReplacementDTO(){}

    public ReplacementDTO(Long sourcePosition, Long targetPosition, Long listId) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.listId = listId;
    }

    public Long getSourcePosition() {
        return sourcePosition;
    }
    public void setSourcePosition(Long sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public Long getTargetPosition() {
        return targetPosition;
    }
    public void setTargetPosition(Long targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Long getListId() {
        return listId;
    }
    public void setListId(Long listId) {
        this.listId = listId;
    }
}

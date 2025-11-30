package com.lucas.dslist.dto;

public class ReplacementRequestDTO {

    private Long sourcePosition;
    private Long targetPosition;
    private Long listId;

    public ReplacementRequestDTO(){}

    public ReplacementRequestDTO(Long sourcePosition, Long targetPosition, Long listId) {
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
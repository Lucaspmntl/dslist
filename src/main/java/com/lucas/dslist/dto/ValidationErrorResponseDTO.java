package com.lucas.dslist.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponseDTO {

    private String message;
    private int status;

    private List<FieldErrorDTO> errors;

    public ValidationErrorResponseDTO(){}

    public ValidationErrorResponseDTO(String message){
        this.message = message;
        this.status = 400;
        this.errors =  new ArrayList<>();
    }

    public void addFieldErrors(String field, String message, Object rejectedValue){
        this.errors.add((new FieldErrorDTO(message, field, rejectedValue)));
    }

    public String getMessage() {return message;}
    public int getStatus() {return status;}
    public List<FieldErrorDTO> getErrors() {return errors;}


    public static class FieldErrorDTO {
        private String field;
        private String message;
        private Object rejectedValue;

        public FieldErrorDTO(){}
        public FieldErrorDTO(String message, String field, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public String getField() {return field;}
        public String getMessage() {return message;}
        public Object getRejectedValue() {return rejectedValue;}
    }

    public static class ReplacementRequestDTO {

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
}
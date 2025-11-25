package com.lucas.dslist.dto;

import java.util.Objects;

public class GenericResponseDTO {

    private String message;
    private Long id;



    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public GenericResponseDTO(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public GenericResponseDTO(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GenericResponseDTO that = (GenericResponseDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


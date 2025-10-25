package com.lucas.dslist.dto;

import java.util.Objects;

public class NewGameListDTO {

    String name;

    public NewGameListDTO(){}

    public NewGameListDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NewGameListDTO that = (NewGameListDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

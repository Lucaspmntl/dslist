package com.lucas.dslist.models;

import com.lucas.dslist.dto.GameListDTO;
import com.lucas.dslist.dto.NewGameListDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_game_list")
public class GameList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public GameList (){}

    public GameList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GameList(GameListDTO dto){
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public GameList(NewGameListDTO dto){
        this.name = dto.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        GameList gameList = (GameList) o;
        return Objects.equals(id, gameList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

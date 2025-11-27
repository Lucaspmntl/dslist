package com.lucas.dslist.models;

import com.lucas.dslist.projections.BelongingProjection;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_belonging")
public class Belonging {

    @EmbeddedId
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public Belonging(){}

    public Belonging(Game game, GameList gameList, Integer position) {
        this.id.setGame(game);
        this.id.setList(gameList);
        this.position = position;
    }
    public Belonging(BelongingProjection projection){
        this.id.setGame(projection.getGame());
        this.id.setList(projection.getList());
        this.position = projection.getPosition();
    }
}

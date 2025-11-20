package com.lucas.dslist.models;

import com.lucas.dslist.projections.BelongingProjection;
import jakarta.persistence.*;

import java.util.Objects;

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

    public BelongingPK getId() {
        return id;
    }
    public void setId(BelongingPK id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Game getGame(){ return id.getGame(); }
    public void setGame(Game game){ this.id.setGame(game); }

    public GameList getGameList(){ return id.getList(); }
    public void setGameList(GameList list){ this.id.setList(list);}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Belonging belonging = (Belonging) o;
        return Objects.equals(id, belonging.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

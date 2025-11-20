package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.BelongingProjection;

public class BelongingDTO {

    private Game game;
    private GameList list;
    private Integer position;

    public BelongingDTO(){};
    public BelongingDTO(Game game, GameList list, Integer position) {
        this.game = game;
        this.list = list;
        this.position = position;
    }
    public BelongingDTO(BelongingProjection projection){
        this.game = projection.getGame();
        this.list = projection.getList();
        this.position = projection.getPosition();
    }

    public Game getGame() {return game;}
    public void setGame(Game game) {this.game = game;}

    public GameList getList() {return list;}
    public void setList(GameList list) {this.list = list;}

    public Integer getPosition() {return position;}
    public void setPosition(Integer position) {this.position = position;}
}

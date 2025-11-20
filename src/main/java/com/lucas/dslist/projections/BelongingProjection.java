package com.lucas.dslist.projections;

import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;

public interface BelongingProjection {

    Long getId();
    Game getGame();
    GameList getList();
    Integer getPosition();
}

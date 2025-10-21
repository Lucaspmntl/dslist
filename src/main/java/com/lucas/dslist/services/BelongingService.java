package com.lucas.dslist.services;

import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.BelongingPK;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import org.springframework.beans.factory.annotation.Autowired;

public class BelongingService {

    @Autowired
    public BelongingPK belongingPK;

    @Autowired
    public GameList list;

    @Autowired
    public Game game;

}

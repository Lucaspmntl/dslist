package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.GameListDTO;
import com.lucas.dslist.dto.GameMinDTO;
import com.lucas.dslist.dto.GameDTO;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.GameMinProjection;
import com.lucas.dslist.repositories.GameRepository;
import com.lucas.dslist.services.GameListService;
import com.lucas.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/list")
public class GameListController {

    @Autowired
    GameListService gameListService;
    @Autowired
    GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        return gameService.findByList(listId);
    }

    @GetMapping(value = "/{listId}")
    public GameListDTO findById(@PathVariable Long listId){
        return gameListService.findById(listId);
    }
}

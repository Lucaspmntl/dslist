package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.services.GameListService;
import com.lucas.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/new")
    public ResponseEntity<GenericResponseDTO> newList(@RequestBody NewGameListDTO dto){
        GameList newList = gameListService.newList(dto);
        GenericResponseDTO response = new GenericResponseDTO("Lista criada com sucesso!", newList.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

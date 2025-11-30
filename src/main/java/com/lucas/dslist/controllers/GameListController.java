package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.dto.game.GameMinResponseDTO;
import com.lucas.dslist.dto.list.GameListDTO;
import com.lucas.dslist.dto.list.NewGameListDTO;
import com.lucas.dslist.dto.list.UpdateListRequestDTO;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.services.GameListService;
import com.lucas.dslist.services.GameService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<GameListDTO>> findAll(){
        List<GameListDTO> lists = gameListService.findAll();

        return ResponseEntity.ok(lists);
    }


    @DeleteMapping(value = "/{listId}")
    public ResponseEntity<MessageDTO> deleteListById(@PathVariable Long listId){
        return ResponseEntity.ok().body(new MessageDTO("Lista com ID " + listId + " com sucesso!"));
    }


    @GetMapping(value = "/{listId}")
    public ResponseEntity<GameListDTO> findById(@PathVariable Long listId){
        GameListDTO list = gameListService.findById(listId);

        return ResponseEntity.ok(list);
    }


    @PostMapping(value = "/new")
    public ResponseEntity<GameListDTO> newList(@Valid @RequestBody NewGameListDTO dto){
        GameListDTO createdList = gameListService.newList(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdList);
    }


    @PutMapping(value = "/{listId}")
    public ResponseEntity<GameListDTO> update(@Valid @RequestBody UpdateListRequestDTO dto,
                                              @PathVariable Long listId){

        GameListDTO updatedList = gameListService.update(dto, listId);
        return ResponseEntity.ok(updatedList);
    }
}

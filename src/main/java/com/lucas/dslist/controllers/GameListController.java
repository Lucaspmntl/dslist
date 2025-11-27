package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.dto.game.GameMinDTO;
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
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @DeleteMapping(value = "/delete/{listId}")
    public ResponseEntity<GenericResponseDTO> deleteListById(@PathVariable long listId){
        return ResponseEntity.ok().body(new GenericResponseDTO("Lista deletada com sucesso!", listId));
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        return gameService.findByList(listId);
    }

    @GetMapping(value = "/{listId}")
    public GameListDTO findById(@PathVariable Long listId){
        return gameListService.findById(listId);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<GenericResponseDTO> newList(@Valid @RequestBody NewGameListDTO dto){
        GameList newList = gameListService.newList(dto);
        GenericResponseDTO response = new GenericResponseDTO("Lista criada com sucesso!", newList.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{listId}")
    public ResponseEntity<GameListDTO> update(@Valid @RequestBody UpdateListRequestDTO dto,
                                              @PathVariable Long listId){

        GameListDTO updatedList = gameListService.update(dto, listId);
        return ResponseEntity.ok(updatedList);
    }
}

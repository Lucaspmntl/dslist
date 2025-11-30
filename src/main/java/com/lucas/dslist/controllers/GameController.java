package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.dto.game.*;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.services.GameListService;
import com.lucas.dslist.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private GameListService gameListService;


    @GetMapping
    public ResponseEntity<List<GameMinResponseDTO>> findAll(){
        List<GameMinResponseDTO> games = gameService.findAll();

        return ResponseEntity.ok(games);
    }


    @GetMapping(value = "/{gameId}")
    public ResponseEntity<GameMaxResponseDTO> findById(@PathVariable Long gameId){
        GameMaxResponseDTO game = gameService.findById(gameId);

        return ResponseEntity.ok(game);
    }


    @GetMapping(value = "/{listId}/games")
    public ResponseEntity<List<GameMinResponseDTO>> findByList(@PathVariable Long listId){
        List<GameMinResponseDTO> games = gameService.findByList(listId);

        return ResponseEntity.ok(games);
    }


    @PostMapping(value = "/replacement")
    public ResponseEntity<ReplacementRequestDTO> movePosition (@Valid @RequestBody ReplacementRequestDTO dto){
        gameListService.moveGamePosition(dto);

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<GameMaxResponseDTO> newGame(@Valid @RequestBody NewGameRequestDTO dto){
        GameMaxResponseDTO game = gameService.newGame(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }


    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<MessageDTO> deleteById(@PathVariable long gameId){
        gameService.deleteGameById(gameId);

        return ResponseEntity.ok(new MessageDTO("Produto com id " + gameId + " deletado com sucesso"));
    }


    @PutMapping(value = "/{gameId}")
    public ResponseEntity<GameMaxResponseDTO> update(@Valid @RequestBody UpdateGameRequestDTO dto,
                                                     @PathVariable long gameId){

        GameMaxResponseDTO updatedGame = gameService.update(dto, gameId);
        return ResponseEntity.ok(updatedGame);
    }

    @PostMapping(value = "/")
    public ResponseEntity<MessageDTO> addGameInList(@RequestBody AddedGamesInListRequestDTO dto){
        gameService.addGameInList(dto.getGamesId(), dto.getListId());

        MessageDTO response = new MessageDTO("Jogos adicionados à lista " + dto.getListId() + " com sucesso.");
        return ResponseEntity.ok(response);
    }
}

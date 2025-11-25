package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.services.GameListService;
import com.lucas.dslist.services.GameService;
import org.apache.coyote.Response;
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
    public List<GameMinDTO> findAll(){
        System.out.println();
        return gameService.findAll();
    }

    @GetMapping(value = "/{gameId}")
    public GameDTO findById(@PathVariable Long gameId){
        return gameService.findById(gameId);
    }

    @PostMapping(value = "/replacement")
    public ResponseEntity<ReplacementDTO> movePosition (@RequestBody ReplacementDTO replacementDTO){
        gameListService.moveGamePosition(replacementDTO);
        return ResponseEntity.ok(replacementDTO);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> newGame(@RequestBody NewGameDTO dto){
        Game game = gameService.newGame(dto);
        GenericResponseDTO response = new GenericResponseDTO("Jogo criado com Sucesso!", game.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<String> deleteById(@PathVariable long gameId){
        gameService.deleteGameById(gameId);
        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

    @PutMapping(value = "/{gameId}")
    public ResponseEntity<GameDTO> update(@RequestBody GameDTO dto,
                                          @PathVariable long gameId){

        GameDTO updatedGame = gameService.update(dto, gameId);
        return ResponseEntity.ok(updatedGame);
    }
}

package com.lucas.dslist.services;

import com.lucas.dslist.dto.game.GameMaxResponseDTO;
import com.lucas.dslist.dto.game.GameMinResponseDTO;
import com.lucas.dslist.dto.game.NewGameRequestDTO;
import com.lucas.dslist.dto.game.UpdateGameRequestDTO;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.BelongingProjection;
import com.lucas.dslist.repositories.BelongingRepository;
import com.lucas.dslist.repositories.GameListRepository;
import com.lucas.dslist.repositories.GameRepository;
import com.lucas.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameListRepository gameListRepository;
    @Autowired
    private BelongingRepository belongingRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public GameMaxResponseDTO findById(Long gameId){

        GameMaxResponseDTO games = gameRepository.findById(gameId)
                .map(GameMaxResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID " + gameId));

        return games;
    }


    @Transactional(readOnly = true)
    public List<GameMinResponseDTO> findAll(){

        List<GameMinResponseDTO> games = gameRepository.findAll()
                .stream()
                .map(GameMinResponseDTO::new)
                .toList();

        return games;
    }


    @Transactional
    public List<GameMinResponseDTO> findByList(Long listId){
        List<GameMinResponseDTO> games = gameRepository.searchByList(listId)
                .stream()
                .map(GameMinResponseDTO::new)
                .toList();

        if (games.isEmpty())
            throw new ResourceNotFoundException("A lista de id " + listId + " não existe ou não contém jogos");

        return games;
    }


    @Transactional
    public GameMaxResponseDTO newGame(NewGameRequestDTO dto){
        Game newGame = gameRepository.save(new Game(dto));



        return new GameMaxResponseDTO(newGame);
    }


    @Transactional
    public void deleteGameById(Long id) {

        List<Long> listsIds = belongingRepository.findListsWhereGameLocated(id);
        if (listsIds.isEmpty())
            throw new ResourceNotFoundException("Jogo não alocado em uma lista ou lista não encontrada, verifique a requisição.");


        int removedPosition = belongingRepository.findByGameId(id)
                .map(BelongingProjection::getPosition)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID " + id));


        for (Long listId : listsIds) {

            long gameListId = listId;
            belongingRepository.reorderPositionSequence(removedPosition, gameListId);
        }

        belongingRepository.deleteByGameId(id);
    }

    @Transactional
    public GameMaxResponseDTO update(UpdateGameRequestDTO dto, long gameId){

        Game updatedGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o Id " + gameId));

        if (dto.getGenre() != null)
            updatedGame.setGenre(dto.getGenre());

        if (dto.getImgUrl() != null)
            updatedGame.setImgUrl(dto.getImgUrl());

        if (dto.getScore() != null)
            updatedGame.setScore(dto.getScore());

        if (dto.getPlatforms() != null)
            updatedGame.setPlatforms(dto.getPlatforms());

        if (dto.getTitle() != null)
            updatedGame.setTitle(dto.getTitle());

        if (dto.getLongDescription() != null)
            updatedGame.setLongDescription(dto.getLongDescription());

        if (dto.getShortDescription() != null)
            updatedGame.setShortDescription(dto.getShortDescription());

        if (dto.getYear() != null)
            updatedGame.setYear(dto.getYear());

        gameRepository.save(updatedGame);
        return new GameMaxResponseDTO(updatedGame);
    }

    public void addGameInList(List<Long> gameId, Long listId){
        GameList list = gameListRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com o ID " + listId));

        // User user = userRepository.findById(userId)
        //        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrada com o ID " + userId));

        for (Long id : gameId){
            boolean notInTheList = belongingRepository.findByGameAndList(id, listId).isEmpty();

            Game game = gameRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID " + id));

            Integer maxPosition = belongingRepository.findMaxPositionByListId(listId);
            int nextPosition = (maxPosition == null) ? 0 : maxPosition+1;

            if (notInTheList)
                belongingRepository.save(new Belonging(game, list, nextPosition));
        }
    }
}

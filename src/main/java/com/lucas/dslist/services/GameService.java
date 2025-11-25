package com.lucas.dslist.services;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.BelongingProjection;
import com.lucas.dslist.repositories.BelongingRepository;
import com.lucas.dslist.repositories.GameListRepository;
import com.lucas.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameListRepository gameListRepository;
    @Autowired
    private BelongingRepository belongingRepository;


    @Transactional(readOnly = true)
    public GameDTO findById(Long gameId){

        GameDTO result = gameRepository.findById(gameId)
                .map(GameDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID " + gameId));

        return result;
    }


    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){

        List<GameMinDTO> dtoList = gameRepository.findAll()
                .stream()
                .map(GameMinDTO::new)
                .toList();

        return dtoList;
    }


    @Transactional
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinDTO> dtoList = gameRepository.searchByList(listId)
                .stream()
                .map(GameMinDTO::new)
                .toList();

        if (dtoList.isEmpty())
            throw new ResourceNotFoundException("A lista de id " + listId + " não existe ou não contém jogos");

        return dtoList;
    }


    @Transactional
    public Game newGame(NewGameDTO dto){
        Game newGame = gameRepository.save(new Game(dto));

        Integer maxPosition = belongingRepository.findMaxPositionByListId(dto.getListId());
        int nextPosition = (maxPosition == null) ? 0 : maxPosition+1;

        Optional<GameList> gameList = gameListRepository.findById(dto.getListId());
        if (gameList.isEmpty())
            throw new ResourceNotFoundException("A lista com o Id " + dto.getListId() + " não existe");

        belongingRepository.save(new Belonging(newGame, gameList.get(), nextPosition));

        return newGame;
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
    public GameDTO update(GameDTO dto, long gameId){

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
        return new GameDTO(updatedGame);
    }
}

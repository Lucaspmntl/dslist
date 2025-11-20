package com.lucas.dslist.services;

import com.lucas.dslist.dto.*;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.BelongingProjection;
import com.lucas.dslist.projections.GameMinProjection;
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
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }


    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){

        List<Game> list = gameRepository.findAll();
        List<GameMinDTO> dtoList = list.
                stream().
                map(obj -> new GameMinDTO(obj)).
                toList();

        System.out.println();
        return dtoList;
    }


    @Transactional
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        return list.
                stream()
                .map(obj -> new GameMinDTO(obj))
                .toList();
    }


    @Transactional
    public Game newGame(NewGameDTO dto){
        Game newGame = gameRepository.save(new Game(dto));

        Integer maxPosition = belongingRepository.findMaxPositionByListId(dto.getListId());
        int nextPosition = (maxPosition == null) ? 0 : maxPosition+1;

        Optional<GameList> gameList = gameListRepository.findById(dto.getListId());

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
}

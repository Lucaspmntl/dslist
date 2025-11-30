package com.lucas.dslist.services;

import com.lucas.dslist.dto.ReplacementRequestDTO;
import com.lucas.dslist.dto.list.GameListDTO;
import com.lucas.dslist.dto.list.NewGameListDTO;
import com.lucas.dslist.dto.list.UpdateListRequestDTO;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.GameMinProjection;
import com.lucas.dslist.repositories.BelongingRepository;
import com.lucas.dslist.repositories.GameListRepository;
import com.lucas.dslist.repositories.GameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    BelongingRepository belongingRepository;


    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameListDTO> lists = gameListRepository.findAll()
                .stream()
                .map(GameListDTO::new)
                .toList();

        return lists;
    }


    @Transactional(readOnly = true)
    public GameListDTO findById(Long listId){
        GameListDTO list = gameListRepository.findById(listId)
                .map(GameListDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com o Id " + listId));

        return list;
    }


    @Transactional
    public void deleteById(long listId){
        gameListRepository.findById(listId)
                        .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com o ID " + listId));

        belongingRepository.deleteAllByListId(listId);
        gameListRepository.deleteById(listId);
    }


    @Transactional
    public GameListDTO newList(NewGameListDTO dto){
        GameList createdList = gameListRepository.save(new GameList(dto));

        return new GameListDTO(createdList);
    }


    @Transactional(readOnly = false)
    public void moveGamePosition(@Valid ReplacementRequestDTO moveObj) {
        Long listId = moveObj.getListId();
        Long targetPosition = moveObj.getTargetPosition();
        Long sourcePosition = moveObj.getSourcePosition();

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        if (list.isEmpty())
            throw new ResourceNotFoundException("Lista não encontrada com o ID: " + listId);

        GameMinProjection game = list.stream()
            .filter(e -> e.getPosition() == sourcePosition.intValue())
            .findFirst()
            .get();

        list.remove(game);
        list.add(targetPosition.intValue(), game);

        int max = Math.max(sourcePosition.intValue(), targetPosition.intValue());
        int min = Math.min(sourcePosition.intValue(), targetPosition.intValue());

        for (int i = min; i <= max ; i++) {
            Long targetGameId = list.get(i).getId();
            belongingRepository.updateBelongingPosition(listId, targetGameId, i);
        }
    }


    @Transactional
    public GameListDTO update(UpdateListRequestDTO dto, Long listId){
        GameList updatedList = gameListRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com o Id " + listId));

        if (dto.getName() != null)
            updatedList.setName(dto.getName());

        if (dto.getDescription() != null)
            updatedList.setDescription(dto.getDescription());

        gameListRepository.save(updatedList);

        return new GameListDTO(updatedList);
    }
}

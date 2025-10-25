package com.lucas.dslist.services;

import com.lucas.dslist.dto.GameListDTO;
import com.lucas.dslist.dto.GameMinDTO;
import com.lucas.dslist.dto.NewGameListDTO;
import com.lucas.dslist.dto.ReplacementDTO;
import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.BelongingPK;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BelongingRepository belongingRepository;

    @Transactional
    public void newList(NewGameListDTO dto){
        gameListRepository.insertNewList(dto.getName());
    }

    @Transactional(readOnly = false)
    public void moveGamePosition(ReplacementDTO moveObj) {
        Long listId = moveObj.getListId();
        Long targetPosition = moveObj.getTargetPosition();
        Long sourcePosition = moveObj.getSourcePosition();

        List<GameMinProjection> list = gameRepository.searchByList(listId);

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

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id){
        GameList result = gameListRepository.findById(id).get();
        return new GameListDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> list = gameListRepository.findAll();
        List<GameListDTO> dtoList = list.
                stream().
                map(obj -> new GameListDTO(obj)).
                toList();
        return dtoList;
    }

}

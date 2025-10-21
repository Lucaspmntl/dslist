package com.lucas.dslist.services;

import com.lucas.dslist.dto.GameListDTO;
import com.lucas.dslist.dto.GameMinDTO;
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

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    BelongingRepository belongingRepository;

    @Transactional(readOnly = false)
    public void moveGamePosition(int gameId, long listId, int positionTarget) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection game = list.stream().
                filter(e -> e.getId() == gameId)
                .findFirst()
                .get();

        list.remove(game.getId());
        list.add(positionTarget, game);


        long max = Math.max(game.getId(), positionTarget);
        long min = Math.min(game.getId(), positionTarget);


        for (int i = (int) min; i <= max ; i++) {

            int counter = 0;
            Long newGameId = list.get(i).getId();
            Long newGamePosition = Long.valueOf(list.get(i).getPosition());

            belongingRepository.updateBelongingTable(newGameId, newGamePosition, listId);
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

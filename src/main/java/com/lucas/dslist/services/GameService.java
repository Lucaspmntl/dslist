package com.lucas.dslist.services;

import com.lucas.dslist.dto.GameMinDTO;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll(){
        List<Game> gameList = gameRepository.findAll();
        List<GameMinDTO> dtoList = gameList.stream().
                                            map(obj -> new GameMinDTO(obj)).
                                            toList();
        return dtoList;
    }

}

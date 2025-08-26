package com.lucas.dslist.services;

import com.lucas.dslist.dto.GameDTO;
import com.lucas.dslist.dto.GameMinDTO;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> gameList = gameRepository.findAll();
        List<GameMinDTO> dtoList = gameList.stream().
                                            map(obj -> new GameMinDTO(obj)).
                                            toList();
        return dtoList;
    }

}

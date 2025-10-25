package com.lucas.dslist.repositories;

import com.lucas.dslist.dto.NewGameListDTO;
import com.lucas.dslist.models.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "insert into tb_game_list (name)\n" +
            "values (:name)")
    void insertNewList(String name);

}

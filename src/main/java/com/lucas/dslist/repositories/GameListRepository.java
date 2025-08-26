package com.lucas.dslist.repositories;

import com.lucas.dslist.models.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}

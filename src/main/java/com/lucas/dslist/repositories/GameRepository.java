package com.lucas.dslist.repositories;

import com.lucas.dslist.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

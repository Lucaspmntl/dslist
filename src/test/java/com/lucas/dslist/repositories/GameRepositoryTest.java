package com.lucas.dslist.repositories;

import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.models.BelongingPK;
import com.lucas.dslist.models.Game;
import com.lucas.dslist.models.GameList;
import com.lucas.dslist.projections.GameMinProjection;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
class GameRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    GameRepository gameRepository;

    @Test
    @DisplayName("Deve retornar sucesso ao buscar jogos pela lista")
    void shouldReturnGamesWhenListExists() {

        Game game = new Game();
        game.setTitle("Elden Ring");
        entityManager.persist(game);

        GameList list = new GameList();
        list.setName("Role play game");
        entityManager.persist(list);

        Belonging belonging = new Belonging();
        belonging.setId(new BelongingPK(game, list));
        belonging.setPosition(0);
        entityManager.persist(belonging);

        entityManager.flush();

        List<GameMinProjection> result = gameRepository.searchByList(list.getId());

        Assertions.assertThat(result)
                .isNotNull()
                .extracting("title")
                .contains("Elden Ring");
    }


    @Test
    @DisplayName("Deve retornar sucesso em caso de falha ao buscar jogos pela lista")
    void shouldReturnEmptyWhenGamesis() {
        List<GameMinProjection> result = gameRepository.searchByList(540L);

        Assertions.assertThat(result)
                .isEmpty();
    }
}
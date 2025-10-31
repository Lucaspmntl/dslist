package com.lucas.dslist.repositories;

import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.projections.BelongingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BelongingRepository extends JpaRepository<Belonging, Long> {

    @Query(nativeQuery = true, value = "SELECT LIST_ID\n" +
            "FROM TB_BELONGING\n" +
            "WHERE GAME_ID = :gameId")
    Long findListByGameId(Long gameId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_belonging " +
            "SET position = :newPosition " +
            "WHERE list_id = :listId AND game_id = :gameId")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO TB_BELONGING " +
            "(game_id, list_id, position) " +
            "VALUES (:gameId, :listId, :position")
    void newBelonging(long gameId, long listId, int position);

    @Query(nativeQuery = true, value = "SELECT MAX(position) " +
            "FROM TB_BELONGING " +
            "WHERE LIST_ID = :listId")
    Integer findMaxByListId(long listId);
}
package com.lucas.dslist.repositories;

import com.lucas.dslist.models.Belonging;
import com.lucas.dslist.projections.BelongingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BelongingRepository extends JpaRepository<Belonging, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM TB_BELONGING " +
            "WHERE LIST_ID = :listId")
    void deleteAllByListId(Long listId);


    @Query(nativeQuery = true, value = "SELECT * FROM TB_BELONGING " +
            "WHERE GAME_ID = :id")
    Optional<BelongingProjection> findByGameId(Long id);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_belonging " +
            "SET position = :newPosition " +
            "WHERE list_id = :listId AND game_id = :gameId")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);


    @Query(nativeQuery = true, value = "SELECT MAX(position) " +
            "FROM TB_BELONGING " +
            "WHERE LIST_ID = :listId")
    Integer findMaxPositionByListId(Long listId);


    @Query(nativeQuery = true, value = "SELECT DISTINCT LIST_ID FROM TB_BELONGING WHERE GAME_ID = :gameId")
    List<Long> findListsWhereGameLocated(Long gameId);


    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM TB_BELONGING WHERE GAME_ID = :id")
    void deleteByGameId(Long id);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE TB_BELONGING " +
            "SET POSITION = POSITION - 1 " +
            "WHERE POSITION > :position AND " +
            "LIST_ID = :listId")
    void reorderPositionSequence(Integer position, Long listId);


    @Modifying
    @Query(nativeQuery = true,
            value = "SELECT FROM TB_BELONGING" +
            "WHERE GAME_ID = :gameId" +
            "AND LIST_ID = :listId")
    List<BelongingProjection> findByGameAndList(Long gameId, Long listId);
}
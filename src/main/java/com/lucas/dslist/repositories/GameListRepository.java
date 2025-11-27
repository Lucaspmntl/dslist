package com.lucas.dslist.repositories;

import com.lucas.dslist.models.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM TB_USER WHERE USER_ID = :userId")
    void deleteByUserId(Long userId);
}

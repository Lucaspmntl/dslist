package com.lucas.dslist.models;

import com.lucas.dslist.dto.list.GameListDTO;
import com.lucas.dslist.dto.list.NewGameListDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "tb_game_list")
public class GameList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "TEXT", length = 100)
    private String description;


    public GameList(GameListDTO dto){
        BeanUtils.copyProperties(dto,this);
    }

    public GameList(NewGameListDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}

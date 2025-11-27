package com.lucas.dslist.models;

import com.lucas.dslist.dto.game.NewGameRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_game")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "game_year")
    private int year;

    private String title;
    private String genre;
    private String platforms;
    private Double score;

    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Game() {};

    public Game(String longDescription, String shortDescription, String imgUrl, Double score, String platforms, String genre, int year, String title, long id) {
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.imgUrl = imgUrl;
        this.score = score;
        this.platforms = platforms;
        this.genre = genre;
        this.year = year;
        this.title = title;
        this.id = id;
    };

    public Game(NewGameRequestDTO dto){
        this.longDescription = dto.getLongDescription();
        this.shortDescription = dto.getShortDescription();
        this.imgUrl = dto.getImgUrl();
        this.score = dto.getScore();
        this.platforms = dto.getPlatforms();
        this.genre = dto.getGenre();
        this.year = dto.getYear();
        this.title = dto.getTitle();
    }
}


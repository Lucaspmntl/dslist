package com.lucas.dslist.models;

import com.lucas.dslist.dto.NewGameDTO;
import jakarta.persistence.*;
import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "tb_game")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "game_year")
    private int year;

    private String title;
    private String genre;
    private String platforms;
    private double score;

    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Game() {};

    public Game(String longDescription, String shortDescription, String imgUrl, double score, String platforms, String genre, int year, String title, long id) {
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

    public Game(NewGameDTO dto){
        this.longDescription = dto.getLongDescription();
        this.shortDescription = dto.getShortDescription();
        this.imgUrl = dto.getImgUrl();
        this.score = dto.getScore();
        this.platforms = dto.getPlatforms();
        this.genre = dto.getGenre();
        this.year = dto.getYear();
        this.title = dto.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}


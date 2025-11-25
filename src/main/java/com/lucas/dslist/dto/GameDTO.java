package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import org.springframework.beans.BeanUtils;

public class GameDTO {
    
    private Integer year;
    private String title;
    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;
    private Long id;
    private String shortDescription;
    private String LongDescription;

    public GameDTO (){}

    public GameDTO(Integer year, String title, String genre, String platforms, Double score, String imgUrl, Long id, String shortDescription, String LongDescription) {
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.platforms = platforms;
        this.score = score;
        this.imgUrl = imgUrl;
        this.id = id;
        this.shortDescription = shortDescription;
        this.LongDescription = LongDescription;
    }

    public GameDTO (Game entity){
        BeanUtils.copyProperties(entity, this);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String LongDescription) {
        this.LongDescription = LongDescription;
    }
}

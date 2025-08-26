package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import org.springframework.beans.BeanUtils;

public class GameDTO {

    private int year;
    private String title;
    private String genre;
    private String platforms;
    private double score;
    private String imgUrl;
    private long id;
    private String shortDescription;
    private String longDescription;

    GameDTO (){}

    public GameDTO(int year, String title, String genre, String platforms, double score, String imgUrl, long id, String shortDescription, String longDescription) {
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.platforms = platforms;
        this.score = score;
        this.imgUrl = imgUrl;
        this.id = id;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public GameDTO (Game entity){
        BeanUtils.copyProperties(entity, this);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

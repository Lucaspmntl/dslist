package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;

public class GameMinDTO {
    private long id;
    private int year;
    private String title;
    private double score;
    private String imgUrl;
    private String shortDescription;

    public GameMinDTO(Game entity){
        id = entity.getId();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        title = entity.getTitle();
        shortDescription = entity.getShortDescription();
    }

    public GameMinDTO(){
    }

    public GameMinDTO(long id, int year, String title, double score, String imgUrl, String shortDescription) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.score = score;
        this.imgUrl = imgUrl;
        this.shortDescription = shortDescription;
    }

    public long getId(){
        return id;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}

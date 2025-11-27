package com.lucas.dslist.dto;

import java.util.Objects;

public class NewGameRequestDTO {

    private Integer year;
    private String title;
    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;
    private String shortDescription;
    private String longDescription;
    private Long listId;

    public NewGameRequestDTO() {}

    public NewGameRequestDTO(Integer year, String title, String genre, String platforms, Double score, String imgUrl, String shortDescription, String longDescription, Long listId) {
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.platforms = platforms;
        this.score = score;
        this.imgUrl = imgUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.listId = listId;
    }

    public long getListId() {
        return listId;
    }
    //public void setListId(long listId) {this.listId = listId;}

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NewGameRequestDTO that = (NewGameRequestDTO) o;
        return year == that.year && Double.compare(score, that.score) == 0 && Objects.equals(title, that.title) && Objects.equals(genre, that.genre) && Objects.equals(platforms, that.platforms) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(longDescription, that.longDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, title, genre, platforms, score, imgUrl, shortDescription, longDescription);
    }
}

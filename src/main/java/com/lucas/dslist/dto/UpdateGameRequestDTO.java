package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;

public class UpdateGameRequestDTO {

    @Max(value = 2025, message = "Ano inválido, máximo: 2025.")
    private Integer year;

    @Size(max = 25, min = 3, message = "Título inválido, deve conter entre 3 e 25 caracteres.")
    private String title;

    @Size(max = 25, message = "Gênero inválido, deve conter no máximo 25 caracteres.")
    private String genre;

    private String platforms;

    @Max(value = 10, message = "O valor máximo do score deve ser 10.")
    private Double score;

    private String imgUrl;

    private Long id;

    @Size(max = 50, min = 10, message = "A descrição curta deve conter entre 10 e 50 caracteres.")
    private String shortDescription;

    @Size(max = 200, min = 10, message = "A descrição longa deve conter entre 10 e 200 caracteres.")
    private String LongDescription;

    public UpdateGameRequestDTO (){}

    public UpdateGameRequestDTO(Integer year, String title, String genre, String platforms, Double score, String imgUrl, Long id, String shortDescription, String LongDescription) {
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

    public UpdateGameRequestDTO (Game entity){
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

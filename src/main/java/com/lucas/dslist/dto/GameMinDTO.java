package com.lucas.dslist.dto;

import com.lucas.dslist.models.Game;
import com.lucas.dslist.projections.GameMinProjection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GameMinDTO {

    private Long id;

    @Max(value = 2025, message = "Ano inválido, máximo: 2025.")
    private Integer year;

    @Size(max = 25, min = 3, message = "Título inválido, deve conter entre 3 e 25 caracteres.")
    @NotBlank(message = "O título deve ser preenchido.")
    private String title;

    @Max(value = 10, message = "O valor máximo do score deve ser 10.")
    @NotBlank(message = "O score deve ser preenchido.")
    private Double score;

    @NotBlank
    private String imgUrl;

    @Size(max = 50, min = 10, message = "A descrição curta deve conter entre 10 e 50 caracteres.")
    @NotBlank
    private String shortDescription;

    public GameMinDTO(Game entity){
        id = entity.getId();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        title = entity.getTitle();
        shortDescription = entity.getShortDescription();
    }

    public GameMinDTO(GameMinProjection projection){
        id = projection.getId();
        year = projection.getGameYear();
        imgUrl = projection.getImgUrl();
        title = projection.getTitle();
        shortDescription = projection.getShortDescription();
    }

    public GameMinDTO(){
    }

    public GameMinDTO(Long id, Integer year, String title, Double score, String imgUrl, String shortDescription) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.score = score;
        this.imgUrl = imgUrl;
        this.shortDescription = shortDescription;
    }

    public Long getId(){
        return id;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public Integer getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public Double getScore() {
        return score;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}

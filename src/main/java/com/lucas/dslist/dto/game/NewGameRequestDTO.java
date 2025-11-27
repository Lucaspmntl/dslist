package com.lucas.dslist.dto.game;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}

package com.lucas.dslist.projections;

public interface GameMinProjection {

    Long getId();
    Integer getGameYear();
    String getTitle();
    String getImgUrl();
    String getShortDescription();
    Integer getPosition();
}

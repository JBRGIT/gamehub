package com.gamehub.gamehub_api.dto.response;

import com.gamehub.gamehub_api.entity.GameCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDetailResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private BigDecimal price;
    private String developer;
    private String publisher;
    private GameCategory category;
    private String coverImageUrl;
    private Boolean available;
}

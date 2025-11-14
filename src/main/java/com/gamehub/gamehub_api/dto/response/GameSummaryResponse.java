package com.gamehub.gamehub_api.dto.response;

import com.gamehub.gamehub_api.entity.GameCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GameSummaryResponse {
    private Long id;
    private String title;
    private BigDecimal price;
    private GameCategory category;
    private String coverImageUrl;
    private Boolean available;

}

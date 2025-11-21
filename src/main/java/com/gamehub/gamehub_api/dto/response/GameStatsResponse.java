package com.gamehub.gamehub_api.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GameStatsResponse {
    private List<CategoryCountResponse> countByCategory;
    private List<CategoryAvgPriceResponse> avgPriceByCategory;
    private List<GameSummaryResponse> mostExpensive;
    private List<GameSummaryResponse> lessExpensive;

}

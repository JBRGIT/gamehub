package com.gamehub.gamehub_api.dto.response;

import com.gamehub.gamehub_api.entity.GameCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryAvgPriceResponse {
    private GameCategory category;
    private Double averagePrice;
}

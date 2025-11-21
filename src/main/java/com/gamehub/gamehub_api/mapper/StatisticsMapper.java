package com.gamehub.gamehub_api.mapper;

import com.gamehub.gamehub_api.dto.response.CategoryAvgPriceResponse;
import com.gamehub.gamehub_api.dto.response.CategoryCountResponse;
import com.gamehub.gamehub_api.entity.GameCategory;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {
    public CategoryCountResponse objectToCategoryCountResponse(Object[] row) {
        CategoryCountResponse dto = new CategoryCountResponse();
        dto.setCategory((GameCategory) row[0]);
        dto.setTotal((Long) row[1]);
        return dto;
    }

    public CategoryAvgPriceResponse objectToCategoryAvgPriceResponse(Object[] row) {
        CategoryAvgPriceResponse dto = new CategoryAvgPriceResponse();
        dto.setCategory((GameCategory) row[0]);
        Double avg = (double) row[1];
        dto.setAveragePrice(Math.round(avg * 100.0) / 100.0);
        return dto;
    }
}

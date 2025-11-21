package com.gamehub.gamehub_api.mapper;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.CategoryAvgPriceResponse;
import com.gamehub.gamehub_api.dto.response.CategoryCountResponse;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.Game;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDetailResponse gameToGameDetailResponse(Game game);

    GameSummaryResponse gameToGameSummaryResponse(Game game);

    Game gameCreateRequestToGame(GameCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void gameUpdateRequestToGame(GameUpdateRequest request, @MappingTarget Game game);

}

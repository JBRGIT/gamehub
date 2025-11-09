package com.gamehub.gamehub_api.mapper;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.Game;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-09T14:31:30+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDetailResponse gameToGameDetailResponse(Game game) {
        if ( game == null ) {
            return null;
        }

        GameDetailResponse gameDetailResponse = new GameDetailResponse();

        return gameDetailResponse;
    }

    @Override
    public GameSummaryResponse gameToGameSummaryResponse(Game game) {
        if ( game == null ) {
            return null;
        }

        GameSummaryResponse gameSummaryResponse = new GameSummaryResponse();

        return gameSummaryResponse;
    }

    @Override
    public Game gameCreateRequestToGame(GameCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Game game = new Game();

        return game;
    }

    @Override
    public void gameUpdateRequestToGame(GameUpdateRequest request, Game game) {
        if ( request == null ) {
            return;
        }
    }
}

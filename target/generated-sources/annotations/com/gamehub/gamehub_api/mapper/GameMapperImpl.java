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
    date = "2025-11-17T17:37:13+0100",
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

        gameDetailResponse.setId( game.getId() );
        gameDetailResponse.setTitle( game.getTitle() );
        gameDetailResponse.setDescription( game.getDescription() );
        gameDetailResponse.setReleaseDate( game.getReleaseDate() );
        gameDetailResponse.setPrice( game.getPrice() );
        gameDetailResponse.setDeveloper( game.getDeveloper() );
        gameDetailResponse.setPublisher( game.getPublisher() );
        gameDetailResponse.setCategory( game.getCategory() );
        gameDetailResponse.setCoverImageUrl( game.getCoverImageUrl() );
        gameDetailResponse.setAvailable( game.getAvailable() );

        return gameDetailResponse;
    }

    @Override
    public GameSummaryResponse gameToGameSummaryResponse(Game game) {
        if ( game == null ) {
            return null;
        }

        GameSummaryResponse gameSummaryResponse = new GameSummaryResponse();

        gameSummaryResponse.setId( game.getId() );
        gameSummaryResponse.setTitle( game.getTitle() );
        gameSummaryResponse.setPrice( game.getPrice() );
        gameSummaryResponse.setCategory( game.getCategory() );
        gameSummaryResponse.setCoverImageUrl( game.getCoverImageUrl() );
        gameSummaryResponse.setAvailable( game.getAvailable() );

        return gameSummaryResponse;
    }

    @Override
    public Game gameCreateRequestToGame(GameCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Game.GameBuilder game = Game.builder();

        game.title( request.getTitle() );
        game.description( request.getDescription() );
        game.releaseDate( request.getReleaseDate() );
        game.price( request.getPrice() );
        game.developer( request.getDeveloper() );
        game.publisher( request.getPublisher() );
        game.category( request.getCategory() );
        game.coverImageUrl( request.getCoverImageUrl() );

        return game.build();
    }

    @Override
    public void gameUpdateRequestToGame(GameUpdateRequest request, Game game) {
        if ( request == null ) {
            return;
        }

        if ( request.getTitle() != null ) {
            game.setTitle( request.getTitle() );
        }
        if ( request.getDescription() != null ) {
            game.setDescription( request.getDescription() );
        }
        if ( request.getReleaseDate() != null ) {
            game.setReleaseDate( request.getReleaseDate() );
        }
        if ( request.getPrice() != null ) {
            game.setPrice( request.getPrice() );
        }
        if ( request.getDeveloper() != null ) {
            game.setDeveloper( request.getDeveloper() );
        }
        if ( request.getPublisher() != null ) {
            game.setPublisher( request.getPublisher() );
        }
        if ( request.getCategory() != null ) {
            game.setCategory( request.getCategory() );
        }
        if ( request.getCoverImageUrl() != null ) {
            game.setCoverImageUrl( request.getCoverImageUrl() );
        }
        if ( request.getAvailable() != null ) {
            game.setAvailable( request.getAvailable() );
        }
    }
}

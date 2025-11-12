package com.gamehub.gamehub_api.service;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.GameCategory;

import java.util.List;

public interface GameService {
    GameDetailResponse createGame(GameCreateRequest request);
    GameDetailResponse getGameById(Long id);
    List<GameSummaryResponse> getAllGames();
    List<GameSummaryResponse> getGamesByCategory(GameCategory category);
    List<GameSummaryResponse> getAvailableGames();
    GameDetailResponse updateGame(Long id, GameUpdateRequest request);
    void deleteGame(Long id);
    void markAsUnavailable(Long id);
}

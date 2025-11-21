package com.gamehub.gamehub_api.service;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.CategoryAvgPriceResponse;
import com.gamehub.gamehub_api.dto.response.CategoryCountResponse;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.GameCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GameService {
    GameDetailResponse createGame(GameCreateRequest request);
    GameDetailResponse getGameById(Long id);
    Page<GameSummaryResponse> getAllGames(Pageable pageable);
    Page<GameSummaryResponse> getGamesByCategory(GameCategory category, Pageable pageable);
    Page<GameSummaryResponse> getAvailableGames(Pageable pageable);
    Page<GameSummaryResponse> getUnAvailableGames(Pageable pageable);
    Page<GameSummaryResponse> getByCategoryAndAvailable(GameCategory category, Boolean available, Pageable pageable);
    GameDetailResponse updateGame(Long id, GameUpdateRequest request);
    void deleteGame(Long id);
    void markAsUnavailable(Long id);
    List<CategoryCountResponse> countGamesByCategory();
    List<CategoryAvgPriceResponse> AvgPriceGamesByCategory();
    List<GameSummaryResponse> mostExpensiveGame();
    List<GameSummaryResponse> leastExpensiveGame();

}

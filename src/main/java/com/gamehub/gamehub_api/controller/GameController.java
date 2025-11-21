package com.gamehub.gamehub_api.controller;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.*;
import com.gamehub.gamehub_api.entity.GameCategory;
import com.gamehub.gamehub_api.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameDetailResponse> createGame(@Valid @RequestBody GameCreateRequest gameCreateRequest) {
        log.info("Requête POST reçue pour créer un jeu : {}", gameCreateRequest.getTitle());
        GameDetailResponse gameDetailResponse = gameService.createGame(gameCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameDetailResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDetailResponse> getGameById(@PathVariable Long id) {
        log.info("Requête GET reçue pour récupérer le jeu ID : {}", id);
        GameDetailResponse gameDetailResponse = gameService.getGameById(id);
        return ResponseEntity.ok(gameDetailResponse);
    }

    @GetMapping
    public ResponseEntity<Page<GameSummaryResponse>> getAllGames(@PageableDefault(size = 1, sort = {"title"}) Pageable pageable, @RequestParam(required = false) GameCategory category, @RequestParam(required = false) Boolean available) {
        log.info("Requête GET reçue pour récupérer les jeux");
        Page<GameSummaryResponse> page;
        if (category == null && available == null) {
            page = gameService.getAllGames(pageable);
        } else if (category == null && available == true) {
            page = gameService.getAvailableGames(pageable);
        } else if (category == null && !available) {
            page = gameService.getUnAvailableGames(pageable);
        } else if (category != null && available == null) {
            page = gameService.getGamesByCategory(category, pageable);
        } else {
            page = gameService.getByCategoryAndAvailable(category, available, pageable);
        }
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDetailResponse> updateGame(@PathVariable Long id, @Valid @RequestBody GameUpdateRequest request) {
        log.info("Requête Put reçue pour mettre à jour le jeu ID : {}", id);
        GameDetailResponse response = gameService.updateGame(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        log.info("Requête DELETE reçue pour supprimer le jeu ID : {}", id);
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/unavailable")
    public ResponseEntity<Void> markAsUnavailable(@PathVariable Long id) {
        log.info("Requête PATCH reçue pour marquer le jeu ID {} comme indisponible", id);
        gameService.markAsUnavailable(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/count")
    public ResponseEntity<List<CategoryCountResponse>> countGamesByCategory() {
        return ResponseEntity.ok(gameService.countGamesByCategory());
    }

    @GetMapping("/stats/avg")
    public ResponseEntity<List<CategoryAvgPriceResponse>> AvgPriceGamesByCategory() {
        return ResponseEntity.ok(gameService.AvgPriceGamesByCategory());
    }

    @GetMapping("/stats/max")
    public ResponseEntity<List<GameSummaryResponse>> mostExpensiveGame() {
        return ResponseEntity.ok(gameService.mostExpensiveGame());
    }

    @GetMapping("/stats/min")
    public ResponseEntity<List<GameSummaryResponse>> leastExpensiveGame() {
        return ResponseEntity.ok(gameService.leastExpensiveGame());
    }

    @GetMapping("/stats/all")
    public ResponseEntity<GameStatsResponse> getAllStatistiques() {
        List<CategoryCountResponse> categoryCountResponses = gameService.countGamesByCategory();
        List<CategoryAvgPriceResponse> categoryAvgPriceResponses = gameService.AvgPriceGamesByCategory();
        List<GameSummaryResponse> mostExpensives = gameService.mostExpensiveGame();
        List<GameSummaryResponse> lessExpensives = gameService.leastExpensiveGame();

        GameStatsResponse gameStatsResponse = GameStatsResponse.builder().countByCategory(categoryCountResponses).avgPriceByCategory(categoryAvgPriceResponses).mostExpensive(mostExpensives).lessExpensive(lessExpensives).build();
        return ResponseEntity.ok(gameStatsResponse);
    }

}

package com.gamehub.gamehub_api.controller;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
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
    public ResponseEntity<Page<GameSummaryResponse>> getAllGames(@PageableDefault(size = 1, sort = {"title"}) Pageable pageable) {
        log.info("Requête GET reçue pour récupérer les jeux");
        Page<GameSummaryResponse> page = gameService.getAllGames(pageable);
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
}

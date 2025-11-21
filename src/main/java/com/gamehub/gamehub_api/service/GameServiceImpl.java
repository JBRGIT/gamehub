package com.gamehub.gamehub_api.service;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.CategoryAvgPriceResponse;
import com.gamehub.gamehub_api.dto.response.CategoryCountResponse;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import com.gamehub.gamehub_api.exception.GameAlreadyExistsException;
import com.gamehub.gamehub_api.exception.GameNotFoundException;
import com.gamehub.gamehub_api.mapper.GameMapper;
import com.gamehub.gamehub_api.mapper.StatisticsMapper;
import com.gamehub.gamehub_api.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final StatisticsMapper statisticsMapper;

    @Override
    public GameDetailResponse createGame(GameCreateRequest gameCreateRequest) {
        log.info("Tentative de création d’un nouveau jeu : {}", gameCreateRequest.getTitle());
        if (gameRepository.findByTitle(gameCreateRequest.getTitle()).isEmpty()) {
            Game game = gameMapper.gameCreateRequestToGame(gameCreateRequest);
            Game savedGame = gameRepository.save(game);
            log.info("Jeu sauvegardé : {} (ID : {})", savedGame.getTitle(), savedGame.getId());
            GameDetailResponse gameDetailResponse = gameMapper.gameToGameDetailResponse(savedGame);
            return gameDetailResponse;
        } else {
            throw new GameAlreadyExistsException(gameCreateRequest.getTitle());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GameDetailResponse getGameById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        log.info("Jeu récupéré : {} (ID : {})", game.getTitle(), id);
        return gameMapper.gameToGameDetailResponse(game);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<GameSummaryResponse> getAllGames(Pageable pageable) {
        Page<Game> games = gameRepository.findAll(pageable);
        log.info("Récupération de tous les jeux : {} trouvés sur {} pages", games.getTotalElements(), games.getTotalPages());
        log.info("Cette page {} contient {} jeux", games.getNumber() + 1, games.getNumberOfElements());
        return games.map(gameMapper::gameToGameSummaryResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GameSummaryResponse> getGamesByCategory(GameCategory category, Pageable pageable) {
        log.info("Récupération des jeux de la catégorie : {}", category);
        Page<Game> gamesPage = gameRepository.findByCategory(category, pageable);
        log.info("{} jeux de catégorie {}", gamesPage.getTotalElements(), category);
        return gamesPage.map(gameMapper::gameToGameSummaryResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameSummaryResponse> getAvailableGames(Pageable pageable) {
        log.info("Récupération des jeux disponibles...");
        Page<Game> gamesPage = gameRepository.findByAvailableTrue(pageable);
        log.info("{} jeux disponibles trouvés", gamesPage.getTotalElements());
        return gamesPage.map(gameMapper::gameToGameSummaryResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameSummaryResponse> getUnAvailableGames(Pageable pageable) {
        log.info("Récupération des jeux indisponibles...");
        Page<Game> gamesPage = gameRepository.findByAvailableFalse(pageable);
        log.info("{} jeux indisponibles trouvés", gamesPage.getTotalElements());
        return gamesPage.map(gameMapper::gameToGameSummaryResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameSummaryResponse> getByCategoryAndAvailable(GameCategory category, Boolean available, Pageable pageable) {
        log.info("Récupération des jeux catégorie = {} avec disponibilité = {}", category, available);
        Page<Game> gamesPage = gameRepository.findByCategoryAndAvailable(category, available, pageable);
        return gamesPage.map(gameMapper::gameToGameSummaryResponse);
    }


    @Override
    public GameDetailResponse updateGame(Long id, GameUpdateRequest request) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        gameMapper.gameUpdateRequestToGame(request, game);
        Game updatedGame = gameRepository.save(game);
        log.info("Mise à jour du jeu : {} (ID : {})", updatedGame.getTitle(), id);
        return gameMapper.gameToGameDetailResponse(updatedGame);
    }

    @Override
    public void deleteGame(Long id) {
        log.info("Tentative de suppression du jeu avec ID : {}", id);
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            log.info("Jeu supprimé avec succès (ID : {})", id);
        } else {
            log.error("Suppression échouée : jeu introuvable (ID : {})", id);
            throw new GameNotFoundException(id);
        }
    }

    @Override
    public void markAsUnavailable(Long id) {
        log.info("Tentative de mise hors ligne du jeu avec ID : {}", id);
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        game.setAvailable(false);
        gameRepository.save(game);
        log.info("Jeu marqué comme indisponible : {} (ID : {})", game.getTitle(), id);
    }

    @Override
    public List<CategoryCountResponse> countGamesByCategory() {
        List<Object[]> objects = gameRepository.countGamesByCategory();
        return objects.stream().map(statisticsMapper::objectToCategoryCountResponse).toList();
    }


    @Override
    public List<CategoryAvgPriceResponse> AvgPriceGamesByCategory() {
        List<Object[]> objects = gameRepository.avgPriceGamesByCategory();
        return objects.stream().map(statisticsMapper::objectToCategoryAvgPriceResponse).toList();
    }

    @Override
    public List<GameSummaryResponse> mostExpensiveGame() {
        List<Game> games = gameRepository.findMostExpensiveGame();
        return games.stream().map(gameMapper::gameToGameSummaryResponse).toList();
    }

    @Override
    public List<GameSummaryResponse> leastExpensiveGame() {
        List<Game> games = gameRepository.findLeastExpensiveGame();
        return games.stream().map(gameMapper::gameToGameSummaryResponse).toList();
    }

}

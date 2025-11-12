package com.gamehub.gamehub_api.service;

import com.gamehub.gamehub_api.dto.request.GameCreateRequest;
import com.gamehub.gamehub_api.dto.request.GameUpdateRequest;
import com.gamehub.gamehub_api.dto.response.GameDetailResponse;
import com.gamehub.gamehub_api.dto.response.GameSummaryResponse;
import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import com.gamehub.gamehub_api.exception.GameAlreadyExistsException;
import com.gamehub.gamehub_api.exception.GameNotFoundException;
import com.gamehub.gamehub_api.mapper.GameMapper;
import com.gamehub.gamehub_api.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

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
    public List<GameSummaryResponse> getAllGames() {
        List<GameSummaryResponse> gameSummaryResponseList = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        for (Game game : games) {
            GameSummaryResponse gameSummaryResponse = gameMapper.gameToGameSummaryResponse(game);
            gameSummaryResponseList.add(gameSummaryResponse);
        }
        log.info("Récupération de tous les jeux : {} trouvés", gameSummaryResponseList.size());
        return gameSummaryResponseList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameSummaryResponse> getGamesByCategory(GameCategory category) {
        log.info("Récupération des jeux de la catégorie : {}", category);
        List<GameSummaryResponse> gameSummaryResponseList = new ArrayList<>();
        List<Game> games = gameRepository.findByCategory(category);
        for (Game game : games) {
            GameSummaryResponse gameSummaryResponse = gameMapper.gameToGameSummaryResponse(game);
            gameSummaryResponseList.add(gameSummaryResponse);
        }
        log.info("{} jeux trouvés pour la catégorie {}", gameSummaryResponseList.size(), category);
        return gameSummaryResponseList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameSummaryResponse> getAvailableGames() {
        log.info("Récupération des jeux disponibles...");
        List<GameSummaryResponse> gameSummaryResponseList = new ArrayList<>();
        List<Game> games = gameRepository.findByAvailableTrue();
        for (Game game : games) {
            GameSummaryResponse gameSummaryResponse = gameMapper.gameToGameSummaryResponse(game);
            gameSummaryResponseList.add(gameSummaryResponse);
        }
        log.info("{} jeux disponibles trouvés", gameSummaryResponseList.size());
        return gameSummaryResponseList;

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
}

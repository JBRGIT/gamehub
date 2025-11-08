package com.gamehub.gamehub_api.repository;

import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTitle(String title);

    List<Game> findByCategory(GameCategory category);

    List<Game> findByAvailableTrue();
}

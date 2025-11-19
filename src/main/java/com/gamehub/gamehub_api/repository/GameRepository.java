package com.gamehub.gamehub_api.repository;

import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTitle(String title);

    Page<Game> findByCategory(GameCategory category, Pageable pageable);

    Page<Game> findByAvailableTrue(Pageable pageable);

    Page<Game> findByAvailableFalse(Pageable pageable);

    Page<Game> findByCategoryAndAvailable(GameCategory category,Boolean available, Pageable pageable);
}

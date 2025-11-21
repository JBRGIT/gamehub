package com.gamehub.gamehub_api.repository;

import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTitle(String title);

    Page<Game> findByCategory(GameCategory category, Pageable pageable);

    Page<Game> findByAvailableTrue(Pageable pageable);

    Page<Game> findByAvailableFalse(Pageable pageable);

    Page<Game> findByCategoryAndAvailable(GameCategory category,Boolean available, Pageable pageable);

    @Query("SELECT g.category, COUNT(g) FROM Game g GROUP BY g.category")
    List<Object[]> countGamesByCategory();
    @Query("SELECT g.category, AVG(g.price) FROM Game g GROUP BY g.category")
    List<Object[]> avgPriceGamesByCategory();
    @Query("SELECT g FROM Game g WHERE g.price = (SELECT MAX(ga.price) from Game ga)")
    List<Game> findMostExpensiveGame();
    @Query("SELECT g FROM Game g WHERE g.price = (SELECT MIN(ga.price) from Game ga)")
    List<Game> findLeastExpensiveGame();


}

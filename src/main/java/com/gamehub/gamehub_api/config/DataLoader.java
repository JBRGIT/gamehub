package com.gamehub.gamehub_api.config;

import com.gamehub.gamehub_api.entity.Game;
import com.gamehub.gamehub_api.entity.GameCategory;
import com.gamehub.gamehub_api.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class DataLoader {
    private final GameRepository gameRepository;

    @Bean
    public CommandLineRunner CommandLineRunner() {

        return args -> {

            if (gameRepository.count() == 0) {

                log.info("Aucun jeu en base — création des données de test...");

                Game g1 = Game.builder()
                        .title("The Witcher 3")
                        .description("RPG mythique")
                        .releaseDate(LocalDate.of(2023, 12, 30))
                        .price(BigDecimal.valueOf(39.99))
                        .developer("CDPR")
                        .publisher("CDPR")
                        .category(GameCategory.RPG)
                        .coverImageUrl("https://example.com/witcher.jpg")
                        .build();

                Game g2 = Game.builder()
                        .title("FIFA 24")
                        .description("Football réaliste")
                        .releaseDate(LocalDate.of(2023, 9, 29))
                        .price(BigDecimal.valueOf(69.99)) // prix max
                        .developer("EA Sports")
                        .publisher("Electronic Arts")
                        .category(GameCategory.SPORT)
                        .coverImageUrl("https://example.com/fifa24.jpg")
                        .build();

                Game g3 = Game.builder()
                        .title("NBA 2K24")
                        .description("Basket ultra réaliste")
                        .releaseDate(LocalDate.of(2023, 9, 20))
                        .price(BigDecimal.valueOf(69.99)) // prix max aussi !
                        .developer("2K")
                        .publisher("2K")
                        .category(GameCategory.SPORT)
                        .coverImageUrl("https://example.com/nba.jpg")
                        .build();

                Game g4 = Game.builder()
                        .title("Minecraft")
                        .description("Créativité et survie")
                        .releaseDate(LocalDate.of(2011, 11, 18))
                        .price(BigDecimal.valueOf(19.99)) // prix min
                        .developer("Mojang")
                        .publisher("Mojang Studios")
                        .category(GameCategory.ADVENTURE)
                        .coverImageUrl("https://example.com/minecraft.jpg")
                        .available(false)
                        .build();

                Game g5 = Game.builder()
                        .title("Stardew Valley")
                        .description("Farm RPG")
                        .releaseDate(LocalDate.of(2016, 2, 26))
                        .price(BigDecimal.valueOf(19.99)) // prix min aussi !
                        .developer("ConcernedApe")
                        .publisher("ConcernedApe")
                        .category(GameCategory.RPG)
                        .coverImageUrl("https://example.com/stardew.jpg")
                        .build();

                gameRepository.save(g1);
                gameRepository.save(g2);
                gameRepository.save(g3);
                gameRepository.save(g4);
                gameRepository.save(g5);

                log.info("5 jeux de test optimisés ont été créés !");

            }
        };
    }
}

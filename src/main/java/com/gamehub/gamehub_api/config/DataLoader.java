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
                        .description("Description du film")
                        .releaseDate(LocalDate.of(2023, 12, 30))
                        .price(BigDecimal.valueOf(39.99))
                        .developer("jib")
                        .publisher("azerty")
                        .category(GameCategory.RPG)
                        .coverImageUrl("https://example.com/witcher.jpg")
                        .available(true)
                        .build();

                Game g2 = Game.builder()
                        .title("FIFA 24")
                        .description("Jeu de football ultra réaliste.")
                        .releaseDate(LocalDate.of(2023, 9, 29))
                        .price(BigDecimal.valueOf(69.99))
                        .developer("EA Sports")
                        .publisher("Electronic Arts")
                        .category(GameCategory.SPORT)
                        .coverImageUrl("https://example.com/fifa24.jpg")
                        .available(true)
                        .build();

                Game g3 = Game.builder()
                        .title("Minecraft")
                        .description("Jeu sandbox basé sur la créativité et la survie.")
                        .releaseDate(LocalDate.of(2011, 11, 18))
                        .price(BigDecimal.valueOf(29.99))
                        .developer("Mojang")
                        .publisher("Mojang Studios")
                        .category(GameCategory.ADVENTURE)
                        .coverImageUrl("https://example.com/minecraft.jpg")
                        .available(false)
                        .build();

                Game g4 = Game.builder()
                        .title("Call of Duty: Modern Warfare 2")
                        .description("FPS nerveux avec campagne et multijoueur.")
                        .releaseDate(LocalDate.of(2022, 10, 28))
                        .price(BigDecimal.valueOf(69.99))
                        .developer("Infinity Ward")
                        .publisher("Activision")
                        .category(GameCategory.ACTION)
                        .coverImageUrl("https://example.com/codmw2.jpg")
                        .available(true)
                        .build();

                Game g5 = Game.builder()
                        .title("Civilization VI")
                        .description("Jeu de stratégie au tour par tour.")
                        .releaseDate(LocalDate.of(2016, 10, 21))
                        .price(BigDecimal.valueOf(59.99))
                        .developer("Firaxis Games")
                        .publisher("2K Games")
                        .category(GameCategory.STRATEGY)
                        .coverImageUrl("https://example.com/civ6.jpg")
                        .available(false)
                        .build();

                gameRepository.save(g1);
                gameRepository.save(g2);
                gameRepository.save(g3);
                gameRepository.save(g4);
                gameRepository.save(g5);

                log.info("5 jeux ont été créés !");
            }
        };
    }
}

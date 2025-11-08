package com.gamehub.gamehub_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate releaseDate;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(length = 100)
    private String developer ;
    @Column(length = 100)
    private String publisher ;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GameCategory category;
    private String coverImageUrl ;
    @NotNull
    private Boolean available = true;

}

package com.gamehub.gamehub_api.dto.request;

import com.gamehub.gamehub_api.entity.GameCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameCreateRequest {
    @NotBlank
    @Size(max = 200, message = "Le titre ne peut pas dépasser 200 caractères")
    private String title;
    @Size(max = 5000, message = "La description ne peut pas dépasser 5000 caractères")
    private String description;
    @PastOrPresent(message = "La date de sortie ne peut pas être dans le futur")
    private LocalDate releaseDate;
    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0", inclusive = true, message = "Le prix doit être positif")
    @DecimalMax(value = "99999999.99", message = "Le prix est trop élevé")
    private BigDecimal price;
    @Size(max = 100, message = "Le nom du développeur ne peut pas dépasser 100 caractères")
    private String developer;
    @Size(max = 100, message = "Le nom de l'éditeur ne peut pas dépasser 100 caractères")
    private String publisher;
    @NotNull(message = "La catégorie est obligatoire")
    @Enumerated(EnumType.STRING)
    private GameCategory category;
    @Pattern(regexp = "^(http|https)://.*$")
    private String coverImageUrl;
}

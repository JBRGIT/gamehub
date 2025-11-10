package com.gamehub.gamehub_api.exception;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id) {
        super("Jeu non trouvé avec l'ID : " + id);
    }

    public GameNotFoundException(String title) {
        super("Jeu non trouvé avec le titre : " + title);
    }
}

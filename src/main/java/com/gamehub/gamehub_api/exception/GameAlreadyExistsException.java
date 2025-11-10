package com.gamehub.gamehub_api.exception;

public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException(String title) {
        super("Un jeu existe déjà avec le titre : " + title);
    }
}

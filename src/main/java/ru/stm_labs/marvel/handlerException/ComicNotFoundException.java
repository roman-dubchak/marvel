package ru.stm_labs.marvel.handlerException;

public class ComicNotFoundException extends RuntimeException {
    public ComicNotFoundException(String message) {
        super(message);
    }
}

package ru.stm_labs.marvel.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.stm_labs.marvel.dto.ExceptionMassageDto;

@ControllerAdvice
public class AppHandlerException {

    @ExceptionHandler
    @ResponseBody
    private ResponseEntity<ExceptionMassageDto> handleComicNotFoundException(ComicNotFoundException e) {
        return new ResponseEntity<>(new ExceptionMassageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    private ResponseEntity<ExceptionMassageDto> handleCharacterNotFoundException(CharacterNotFoundException e) {
        return new ResponseEntity<>(new ExceptionMassageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
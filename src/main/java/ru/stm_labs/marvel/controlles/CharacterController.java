package ru.stm_labs.marvel.controlles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.servicies.CharacterService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public Character get(@PathVariable("id") Long id) {
        return characterService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CharacterDtoRequest> post(@RequestBody @Valid CharacterDtoRequest characterDtoRequest) {
        characterService.save(characterDtoRequest);
        return new ResponseEntity<>(characterDtoRequest, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDtoRequest> put(
            @PathVariable("id") Long id,
            @RequestBody @Valid CharacterDtoRequest characterDtoRequest) {
        characterService.update(characterDtoRequest, id);
        return new ResponseEntity<>(characterDtoRequest, HttpStatus.OK);
    }
}
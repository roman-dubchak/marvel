package ru.stm_labs.marvel.controlles;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.servicies.CharacterService;
import ru.stm_labs.marvel.servicies.ImageCharacterService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    private final CharacterService characterService;
    private final ImageCharacterService imageCharacterService;

    public CharacterController(CharacterService characterService,
                               ImageCharacterService imageCharacterService) {
        this.characterService = characterService;
        this.imageCharacterService = imageCharacterService;
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

    // TODO save images
    @PostMapping(name = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@PathVariable("id") Long id,
                                             @RequestParam(value = "file") MultipartFile file) {
        imageCharacterService.save(characterService.findById(id), file);
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDtoRequest> put(@PathVariable("id") Long id,
                                                   @RequestBody @Valid CharacterDtoRequest characterDtoRequest) {
        characterService.update(characterDtoRequest, id);
        return new ResponseEntity<>(characterDtoRequest, HttpStatus.OK);
    }
}
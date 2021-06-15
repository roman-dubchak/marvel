package ru.stm_labs.marvel.controlles;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.dto.*;
import ru.stm_labs.marvel.dto.page.PageDto;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.servicies.CharacterService;
import ru.stm_labs.marvel.servicies.ComicService;
import ru.stm_labs.marvel.servicies.ImageCharacterService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Api(value = "Контроллер героев")
@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    private final CharacterService characterService;
    private final ImageCharacterService imageCharacterService;
    private final ComicService comicService;

    public CharacterController(CharacterService characterService,
                               ImageCharacterService imageCharacterService,
                               ComicService comicService) {
        this.characterService = characterService;
        this.imageCharacterService = imageCharacterService;
        this.comicService = comicService;
    }

    @ApiOperation(value = "Получение и фильтрация списка героев")
    @GetMapping()
    public ResponseEntity<PageDto<CharacterDtoResponse>> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "resourceUri", required = false) String resourceUri,
            @RequestParam(value = "modified", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modified,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        PageDto<CharacterDtoResponse> response = characterService.findAll(
                new FilterCharacterDto(
                        name,
                        description,
                        resourceUri,
                        modified
                ), page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение и фильтрация списка комиксов по id героя")
    @GetMapping("{id}/comics")
    public ResponseEntity<PageDto<ComicDtoResponse>> getComics(
            @PathVariable("id") Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "format", required = false) String format,
            @RequestParam(value = "pageCount", required = false) Integer pageCount,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "resourceUri", required = false) String resourceUri,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "modified", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime modified,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        PageDto<ComicDtoResponse> response = comicService.findAll(
                new FilterComicDto(
                        id,
                        name,
                        description,
                        format,
                        pageCount,
                        text,
                        resourceUri,
                        price,
                        modified
                ), page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение героя по id")
    @GetMapping("/{id}")
    public Character get(@PathVariable("id") Long id) {
        return characterService.findById(id);
    }

    @ApiOperation(value = "Добавление нового героя")
    @PostMapping(name = "")
    public ResponseEntity<CharacterDtoRequest> post(@RequestBody @Valid CharacterDtoRequest characterDtoRequest) {
        characterService.save(characterDtoRequest);
        return new ResponseEntity<>(characterDtoRequest, HttpStatus.OK);
    }

    // TODO save images
    @ApiOperation(value = "Добавление изображения героя по id")
    @PostMapping(name = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@PathVariable("id") Long id,
                                             @RequestParam(value = "file") MultipartFile file) {
        imageCharacterService.save(characterService.findById(id), file);
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @ApiOperation(value = "Редактирование данных о герое по id")
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDtoRequest> put(@PathVariable("id") Long id,
                                                   @RequestBody @Valid CharacterDtoRequest characterDtoRequest) {
        characterService.update(characterDtoRequest, id);
        return new ResponseEntity<>(characterDtoRequest, HttpStatus.OK);
    }
}
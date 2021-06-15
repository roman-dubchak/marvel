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
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.servicies.CharacterService;
import ru.stm_labs.marvel.servicies.ComicService;
import ru.stm_labs.marvel.servicies.ImageComicService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Api(value = "Контроллер комиксов")
@RestController
@RequestMapping("api/v1/comic")
public class ComicController {

    private final ComicService comicService;
    private final ImageComicService imageComicService;
    private final CharacterService characterService;

    public ComicController(ComicService comicService,
                           ImageComicService imageComicService,
                           CharacterService characterService) {
        this.comicService = comicService;
        this.imageComicService = imageComicService;
        this.characterService = characterService;
    }

    @ApiOperation(value = "Получение и фильтрация списка комиксов")
    @GetMapping("")
    public ResponseEntity<PageDto<ComicDtoResponse>> getComics(
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

    @ApiOperation(value = "Получение и фильтрация списка героев по id комиксов")
    @GetMapping("{id}/character")
    public ResponseEntity<PageDto<CharacterDtoResponse>> getAll(
            @PathVariable("id") Long id,
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
                        id,
                        name,
                        description,
                        resourceUri,
                        modified
                ), page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение комикса по id")
    @GetMapping("/{id}")
    public Comic get(@PathVariable("id") Long id) {
        return comicService.findById(id);
    }

    @ApiOperation(value = "Добавление нового комикса")
    @PostMapping(name = "")
    public ResponseEntity<ComicDtoRequest> post(@RequestBody @Valid ComicDtoRequest comicDtoRequest) {
        comicService.save(comicDtoRequest);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }

    // TODO save images
    @ApiOperation(value = "Добавление изображения комикса по id")
    @PostMapping(name = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@PathVariable("id") Long id,
                                             @RequestParam(value = "file") MultipartFile file) {
        imageComicService.save(comicService.findById(id), file);
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @ApiOperation(value = "Редактирование данных о комиксе по id")
    @PutMapping("/{id}")
    public ResponseEntity<ComicDtoRequest> put(@PathVariable("id") Long id,
                                               @RequestBody @Valid ComicDtoRequest comicDtoRequest) {
        comicService.update(comicDtoRequest, id);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }
}
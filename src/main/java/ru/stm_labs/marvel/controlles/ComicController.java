package ru.stm_labs.marvel.controlles;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.servicies.ComicService;
import ru.stm_labs.marvel.servicies.impl.ImageComicServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/comic")
public class ComicController {

    private final ComicService comicService;
    private final ImageComicServiceImpl imageComicService;

    public ComicController(ComicService comicService, ImageComicServiceImpl imageComicService) {
        this.comicService = comicService;
        this.imageComicService = imageComicService;
    }

    @GetMapping("/{id}")
    public Comic get(@PathVariable("id") Long id) {
        return comicService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ComicDtoRequest> post(@RequestBody @Valid ComicDtoRequest comicDtoRequest) {
        comicService.save(comicDtoRequest);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }

    // TODO save images
    @PostMapping(name = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postUpload(@PathVariable("id") Long id,
                                             @RequestParam(value = "file") MultipartFile file) {
        imageComicService.save(comicService.findById(id), file);
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicDtoRequest> put(@PathVariable("id") Long id,
                                               @RequestBody @Valid ComicDtoRequest comicDtoRequest) {
        comicService.update(comicDtoRequest, id);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }
}
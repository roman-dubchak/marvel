package ru.stm_labs.marvel.controlles;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.servicies.ComicService;
import ru.stm_labs.marvel.servicies.ImagesFileService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/comic")
public class ComicController {

    private final ComicService comicService;
    private final ImagesFileService imagesFileService;

    public ComicController(ComicService comicService, ImagesFileService imagesFileService) {
        this.comicService = comicService;
        this.imagesFileService = imagesFileService;
    }

    @GetMapping("/{id}")
    public Comic get(@PathVariable("id") Long id) {
        return comicService.findById(id);
    }

    // TODO save images
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComicDtoRequest> post(@RequestBody @Valid ComicDtoRequest comicDtoRequest,
                                                @RequestPart MultipartFile file) {
        comicService.save(comicDtoRequest, file);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicDtoRequest> put(
            @PathVariable("id") Long id,
            @RequestBody @Valid ComicDtoRequest comicDtoRequest) {
        comicService.update(comicDtoRequest, id);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }
}
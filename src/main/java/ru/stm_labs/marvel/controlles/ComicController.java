package ru.stm_labs.marvel.controlles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.servicies.ComicService;

@RestController
@RequestMapping("api/v1/comic")
public class ComicController {

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping("/{id}")
    public Comic get(@PathVariable("id") Long id) {
        return comicService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ComicDtoRequest> post(@RequestBody ComicDtoRequest comicDtoRequest) {
        comicService.save(comicDtoRequest);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComicDtoRequest> put(
            @PathVariable("id") Long id,
            @RequestBody ComicDtoRequest comicDtoRequest) {
        comicService.update(comicDtoRequest, id);
        return new ResponseEntity<>(comicDtoRequest, HttpStatus.OK);
    }
}
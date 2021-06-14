package ru.stm_labs.marvel.servicies;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.dto.ImageDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ImageComic;

import java.util.List;

@Service
public interface ImageComicService {

    ImageComic save(Comic comic, MultipartFile file);

}

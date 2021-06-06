package ru.stm_labs.marvel.servicies;

import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;

import java.util.List;

@Service
public interface ComicService {

    Comic findById(Long id);

    Comic save(ComicDtoRequest comicDtoRequest);

    Comic update(ComicDtoRequest ComicDtoRequest, Long id);

    List<Comic> findAll();

}

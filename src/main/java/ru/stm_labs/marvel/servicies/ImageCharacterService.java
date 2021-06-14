package ru.stm_labs.marvel.servicies;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ImageCharacter;
import ru.stm_labs.marvel.entities.ImageComic;

@Service
public interface ImageCharacterService {

    ImageCharacter save(Character character, MultipartFile file);

}

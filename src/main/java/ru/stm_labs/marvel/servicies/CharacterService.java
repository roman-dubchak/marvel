package ru.stm_labs.marvel.servicies;

import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;

import java.util.List;

@Service
public interface CharacterService {

    Character findById(Long id);

    Character save(CharacterDtoRequest characterDtoRequest);

    Character update(CharacterDtoRequest characterDtoRequest, Long id);

    List<Character> findAll();

}

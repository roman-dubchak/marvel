package ru.stm_labs.marvel.servicies;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.dto.CharacterDtoResponse;
import ru.stm_labs.marvel.dto.FilterCharacterDto;
import ru.stm_labs.marvel.dto.page.PageDto;
import ru.stm_labs.marvel.entities.Character;

@Service
public interface CharacterService {

    Character findById(Long id);

    Character save(CharacterDtoRequest characterDtoRequest);

    Character update(CharacterDtoRequest characterDtoRequest, Long id);

    PageDto<CharacterDtoResponse> findAll(FilterCharacterDto filterCharacterDto,
                                          int page, int size, Sort sort);

}

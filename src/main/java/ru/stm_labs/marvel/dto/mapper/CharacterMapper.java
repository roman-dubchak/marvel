package ru.stm_labs.marvel.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;

@Mapper
public interface CharacterMapper {
    CharacterMapper MAPPER = Mappers.getMapper(CharacterMapper.class);

    Character toCharacter(CharacterDtoRequest characterDtoRequest);

    Character toCharacter(Character character);

}

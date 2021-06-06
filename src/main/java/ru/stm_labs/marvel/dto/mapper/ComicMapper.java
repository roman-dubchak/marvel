package ru.stm_labs.marvel.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;

@Mapper
public interface ComicMapper {
    ComicMapper MAPPER = Mappers.getMapper(ComicMapper.class);

    Comic toComic(ComicDtoRequest comicDtoRequest);

    Comic toComic(Comic comic);

}

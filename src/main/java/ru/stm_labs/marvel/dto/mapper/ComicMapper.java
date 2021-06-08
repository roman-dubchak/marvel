package ru.stm_labs.marvel.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.servicies.ComicService;

@Mapper(uses = ComicPriceMapper.class) //(componentModel = "spring", uses = {ComicService.class})
public interface ComicMapper {

    ComicMapper MAPPER = Mappers.getMapper(ComicMapper.class);

    @Mapping(target = "prices", source = "prices", qualifiedByName = "comicPriceList")
    Comic toComic(ComicDtoRequest comicDtoRequest);

}

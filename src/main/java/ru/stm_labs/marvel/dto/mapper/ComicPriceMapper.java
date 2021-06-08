package ru.stm_labs.marvel.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.dto.ComicPriceDtoRequest;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.servicies.ComicService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper //(componentModel = "spring", uses = {ComicService.class, ComicMapper.class}) //(uses = ComicMapper.class)
public interface ComicPriceMapper {

    ComicPriceMapper MAPPER = Mappers.getMapper(ComicPriceMapper.class);

//    @Mapping(target = "comicDtoRequest.prices", ignore = true)
    @Mapping(target = "typeComic", source = "typeComic")
    ComicPrice toComicPrice(ComicPriceDtoRequest comicPriceDtoRequest);

    List<ComicPrice> toComicPriceList(List<ComicPriceDtoRequest> comicPriceDtoRequests);

    @Named("comicPriceList")
    default List<ComicPrice> toComicPriceLs(List<ComicPriceDtoRequest> comicPriceDtoRequests) {
        return comicPriceDtoRequests
                .stream()
                .map(this::toComicPrice)
//                .peek(dto -> dto.setPrice(null))
                .collect(Collectors.toList());
    }

}

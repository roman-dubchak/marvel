package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.entities.TypeComic;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ComicPriceDtoRequest {

    @NotEmpty(message = "Поле type не должно быть пустым!")
    @NotNull(message = "Поле type не должно быть пустым!")
    private String typeComic;

    @NotNull(message = "Поле price не должно быть пустым!")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    private Double price;

    public ComicPrice toComicPriceFromDto(ComicPriceDtoRequest comicPriceDtoRequest, Comic comic) {
        ComicPrice comicPrice = new ComicPrice();
        comicPrice.setTypeComic(TypeComic.typeComic(comicPriceDtoRequest.getTypeComic()));
        comicPrice.setPrice(comicPriceDtoRequest.getPrice());
        comicPrice.setComicId(comic);
        return comicPrice;
    }

    public List<ComicPrice> toComicPriceListFromDto(List<ComicPriceDtoRequest> comicPriceDtoRequests, Comic comic) {
        return comicPriceDtoRequests
                .stream()
                .map(c -> toComicPriceFromDto(c, comic))
                .collect(Collectors.toList());
    }

    public ComicPriceDtoRequest toComicPriceDtoResponse(ComicPrice comicPrice) {
        ComicPriceDtoRequest comicPriceDtoRequest = new ComicPriceDtoRequest();
        comicPriceDtoRequest.setTypeComic(comicPrice.getTypeComic().getTypeComic());
        comicPriceDtoRequest.setPrice(comicPrice.getPrice());
        return comicPriceDtoRequest;
    }

    public List<ComicPriceDtoRequest> toComicPriceDtoResponseList(List<ComicPrice> comicPriceList) {
        return comicPriceList
                .stream()
                .map(c -> (toComicPriceDtoResponse(c)))
                .collect(Collectors.toList());
    }

}

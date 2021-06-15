package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicPrice;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ComicDtoResponse {

    private String title;

    private String description;

    private String format;

    private Integer pageCount;

    private String text;

    private String resourceUri;

    private List<ComicPriceDtoRequest> prices;

    public ComicDtoResponse toComicDtoResponse(Comic comic) {
        ComicDtoResponse comicDtoResponse = new ComicDtoResponse();
        comicDtoResponse.setTitle(comic.getTitle());
        comicDtoResponse.setDescription(comic.getDescription());
        comicDtoResponse.setFormat(comic.getFormat());
        comicDtoResponse.setPageCount(comic.getPageCount());
        comicDtoResponse.setPageCount(comic.getPageCount());
        comicDtoResponse.setText(comic.getText());
        comicDtoResponse.setResourceUri(comic.getResourceUri());
        comicDtoResponse.setPrices(toComicPriceDtoResponseList(comic.getPrices()));
        return comicDtoResponse;
    }

    public List<ComicDtoResponse> toComicDtoResponses(List<Comic> comics) {
        return comics
                .stream()
                .map(c -> toComicDtoResponse(c))
                .collect(Collectors.toList());
    }

    private ComicPriceDtoRequest toComicPriceDtoResponse(ComicPrice comicPrice) {
        ComicPriceDtoRequest comicPriceDtoRequest = new ComicPriceDtoRequest();
        comicPriceDtoRequest.setTypeComic(comicPrice.getTypeComic().getTypeComic());
        comicPriceDtoRequest.setPrice(comicPrice.getPrice());
        return comicPriceDtoRequest;
    }

    private List<ComicPriceDtoRequest> toComicPriceDtoResponseList(List<ComicPrice> comicPriceList) {
        return comicPriceList
                .stream()
                .map(c -> (toComicPriceDtoResponse(c)))
                .collect(Collectors.toList());
    }

}

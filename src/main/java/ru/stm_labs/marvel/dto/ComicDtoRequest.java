package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Comic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ComicDtoRequest {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String format;

    @Min(1)
    private Integer pageCount;

    @NotNull
    @NotEmpty
    private String text;

    @NotNull
    @NotEmpty
    private String resourceUri;

    @NotEmpty
    private List<ComicPriceDtoRequest> prices;

    @NotEmpty
    private List<Long> charactersIds;

    public Comic toComicFromDto(ComicDtoRequest comicDtoRequest) {
        Comic comic = new Comic();
        comic.setTitle(comicDtoRequest.getTitle());
        comic.setDescription(comicDtoRequest.getDescription());
        comic.setFormat(comicDtoRequest.getFormat());
        comic.setPageCount(comicDtoRequest.getPageCount());
        comic.setText(comicDtoRequest.getText());
        comic.setResourceUri(comicDtoRequest.getResourceUri());
        return comic;
    }

}

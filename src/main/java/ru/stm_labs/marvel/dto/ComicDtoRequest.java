package ru.stm_labs.marvel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.entities.ImageComic;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

    @NotNull
    private List<ComicPriceDtoRequest> prices;

}

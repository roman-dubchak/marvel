package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Comic;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ComicPriceDtoRequest {

    @NotEmpty
    @NotNull
    private String typeComic;

    @NotNull
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    private Double price;

    @NotNull
    @Min(1)
    private Long comicId;

}

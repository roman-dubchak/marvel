package ru.stm_labs.marvel.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ImageDtoRequest {

    @NotEmpty
    @NotNull
    private String path;

    @Min(1)
    private Long id;

}

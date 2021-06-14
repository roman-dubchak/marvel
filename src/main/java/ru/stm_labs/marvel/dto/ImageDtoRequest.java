package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.ImageComic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ImageDtoRequest {

    @NotEmpty(message = "Поле path не должно быть пустым!")
    @NotNull(message = "Поле path не должно быть пустым!")
    private String path;

    @Min(value = 1, message = "Минимальное знaчение id = 1")
    private Long comicOrCharacterId;

    public ImageComic toImageComic(ImageDtoRequest imageDtoRequest) {
        ImageComic imageComic = new ImageComic();
        imageComic.setPath(imageDtoRequest.getPath());
        return imageComic;
    }

}

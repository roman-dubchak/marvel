package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.ImageCharacter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageCharacterDto {

    @NotEmpty(message = "Поле path не должно быть пустым!")
    @NotNull(message = "Поле path не должно быть пустым!")
    private String path;

    public ImageCharacterDto toImageDto(ImageCharacter imageCharacter) {
        ImageCharacterDto imageDto = new ImageCharacterDto();
        imageDto.setPath(imageCharacter.getPath());
        return imageDto;
    }

    public List<ImageCharacterDto> toImageDtoList(List<ImageCharacter> characters){
        return characters.stream()
                .map(i -> toImageDto(i))
                .collect(Collectors.toList());
    }

}

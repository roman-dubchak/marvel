package ru.stm_labs.marvel.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicCharacterId;
import ru.stm_labs.marvel.entities.ImageComic;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ComicDtoRequest {

    @NotNull(message = "Поле title не должно быть пустым!")
    @NotEmpty(message = "Поле title не должно быть пустым!")
    private String title;

    @NotNull(message = "Поле description не должно быть пустым!")
    @NotEmpty(message = "Поле description не должно быть пустым!")
    private String description;

    @NotNull(message = "Поле format не должно быть пустым!")
    @NotEmpty(message = "Поле format не должно быть пустым!")
    private String format;

    @Min(value = 1, message = "Минимальное значение pages = 1")
    private Integer pageCount;

    @NotNull(message = "Поле text не должно быть пустым!")
    @NotEmpty(message = "Поле text не должно быть пустым!")
    private String text;

    @NotNull(message = "Поле url не должно быть пустым!")
    @NotEmpty(message = "Поле url не должно быть пустым!")
    private String resourceUri;

    @NotNull(message = "Поле prices не должно быть пустым!")
    @NotEmpty(message = "Поле prices не должно быть пустым!")
    private List<ComicPriceDtoRequest> prices;

    @NotNull(message = "Поле characters id не должно быть пустым!")
    @NotEmpty(message = "Поле characters id не должно быть пустым!")
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

    public List<ComicCharacter> toComicCharacterList(ComicDtoRequest comicDtoRequest, Comic comic) {
        List<ComicCharacter> comicCharacters = comicDtoRequest.getCharactersIds().
                stream()
                .map(i -> comicCharacterCreated(comic.getId(), i))
                .collect(Collectors.toList());

        return comicCharacters;
    }

    private ComicCharacter comicCharacterCreated(Long comicId, Long characterId) {
        ComicCharacter comicCharacter = new ComicCharacter();
        ComicCharacterId comicCharacterId = new ComicCharacterId();
        comicCharacterId.setComicId(comicId);
        comicCharacterId.setCharacterId(characterId);
        comicCharacter.setComicCharacterId(comicCharacterId);
        return comicCharacter;
    }

}

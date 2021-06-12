package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicCharacterId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

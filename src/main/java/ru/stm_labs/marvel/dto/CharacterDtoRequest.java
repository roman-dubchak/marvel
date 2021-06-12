package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicCharacterId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CharacterDtoRequest {

    @NotNull(message = "Поле name не должно быть пустым!")
    @NotEmpty(message = "Поле name не должно быть пустым!")
    private String name;

    @NotNull(message = "Поле description не должно быть пустым!")
    @NotEmpty(message = "Поле description не должно быть пустым!")
    private String description;

    @NotNull(message = "Поле url не должно быть пустым!")
    @NotEmpty(message = "Поле url не должно быть пустым!")
    private String resourceUri;

    private List<Long> comicsIds;

    public Character toCharacterFromDto(CharacterDtoRequest characterDtoRequest) {
        Character character = new Character();
        character.setName(characterDtoRequest.getName());
        character.setDescription(characterDtoRequest.getDescription());
        character.setResourceUri(characterDtoRequest.getResourceUri());
        return character;
    }

    public List<ComicCharacter> toComicCharacterList(CharacterDtoRequest characterDtoRequest, Character character) {
        List<ComicCharacter> comicCharacters = characterDtoRequest.getComicsIds().
                stream()
                .map(i -> comicCharacterCreated(i, character.getId()))
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

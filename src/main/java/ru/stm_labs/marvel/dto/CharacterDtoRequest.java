package ru.stm_labs.marvel.dto;

import lombok.Data;
import ru.stm_labs.marvel.entities.Character;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CharacterDtoRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String resourceUri;

    public Character toCharacterFromDto(CharacterDtoRequest characterDtoRequest) {
        Character character = new Character();
        character.setName(characterDtoRequest.getName());
        character.setDescription(characterDtoRequest.getDescription());
        character.setResourceUri(characterDtoRequest.getResourceUri());
        return character;
    }


}

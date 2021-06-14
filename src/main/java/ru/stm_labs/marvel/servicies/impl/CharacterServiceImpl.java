package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.handlerException.CharacterNotFoundException;
import ru.stm_labs.marvel.handlerException.ComicNotFoundException;
import ru.stm_labs.marvel.repositories.CharacterRepository;
import ru.stm_labs.marvel.repositories.ComicCharacterRepository;
import ru.stm_labs.marvel.repositories.ComicRepository;
import ru.stm_labs.marvel.servicies.CharacterService;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;
    private final ComicCharacterRepository comicCharacterRepository;


    public CharacterServiceImpl(CharacterRepository characterRepository,
                                ComicRepository comicRepository,
                                ComicCharacterRepository comicCharacterRepository) {
        this.characterRepository = characterRepository;
        this.comicRepository = comicRepository;
        this.comicCharacterRepository = comicCharacterRepository;
    }

    @Override
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException(String.format("Герой с id %s не найден!", id)));
    }


    @Transactional
    @Override
    public Character save(CharacterDtoRequest characterDtoRequest) {
        //TODO если нет id
        List<Comic> idComicList = comicRepository.findAllById(characterDtoRequest.getComicsIds());
        if (idComicList.isEmpty()) {
            throw new ComicNotFoundException("Ни один комикс не найден!");
        }

        Character character = characterDtoRequest.toCharacterFromDto(characterDtoRequest);
        System.out.println("Character save: " + character);
        characterRepository.save(character);

        List<ComicCharacter> comicCharacters = characterDtoRequest.toComicCharacterList(characterDtoRequest, character);
        comicCharacterRepository.saveAll(comicCharacters);
        return character;
    }

    @Override
    public Character update(CharacterDtoRequest characterDtoRequest, Long id) {
        Character characterUpd = characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException(String.format("Герой с id %s не найден!", id)));

        Character character = characterDtoRequest.toCharacterFromDto(characterDtoRequest);
        characterUpd.setName(character.getName());
        characterUpd.setDescription(character.getDescription());
        characterUpd.setName(character.getName());
        characterUpd.setResourceUri(character.getResourceUri());

        return characterRepository.save(characterUpd);
    }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }
}

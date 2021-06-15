package ru.stm_labs.marvel.servicies.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.dto.CharacterDtoResponse;
import ru.stm_labs.marvel.dto.FilterCharacterDto;
import ru.stm_labs.marvel.dto.page.CreatePageData;
import ru.stm_labs.marvel.dto.page.PageData;
import ru.stm_labs.marvel.dto.page.PageDto;
import ru.stm_labs.marvel.dto.page.PageDtoCreator;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.handlerException.CharacterNotFoundException;
import ru.stm_labs.marvel.handlerException.ComicNotFoundException;
import ru.stm_labs.marvel.repositories.CharacterRepository;
import ru.stm_labs.marvel.repositories.ComicCharacterRepository;
import ru.stm_labs.marvel.repositories.ComicRepository;
import ru.stm_labs.marvel.repositories.specification.CharacterSpecification;
import ru.stm_labs.marvel.servicies.CharacterService;

import java.util.List;
import java.util.function.Function;

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
    public PageDto<CharacterDtoResponse> findAll(FilterCharacterDto filterCharacterDto,
                                                 int page, int size, Sort sort) {
        PageData pageData = CreatePageData.createPageData(page, size, sort);
        BooleanExpression predicate = CharacterSpecification.createPredicate(
                filterCharacterDto.getIdComic(),
                filterCharacterDto.getName(),
                filterCharacterDto.getDescription(),
                filterCharacterDto.getResourceUri(),
                filterCharacterDto.getModified()
        );

        Page<Character> characterPage = characterRepository.findAll(predicate, pageData);
        Function<Character, CharacterDtoResponse> convert = Character -> toCharacterDtoResponse(Character);

        return PageDtoCreator.createReadQueryResult(characterPage, convert);
    }

    private CharacterDtoResponse toCharacterDtoResponse(Character character){
        return new CharacterDtoResponse().toCharacterDtoResponse(character);
    }
}

package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.repositories.CharacterRepository;
import ru.stm_labs.marvel.repositories.ComicCharacterRepository;
import ru.stm_labs.marvel.repositories.ComicPriceRepository;
import ru.stm_labs.marvel.repositories.ComicRepository;
import ru.stm_labs.marvel.servicies.ComicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;
    private final ComicPriceRepository comicPriceRepository;
    private final ComicCharacterRepository comicCharacterRepository;
    private final CharacterRepository characterRepository;

    public ComicServiceImpl(ComicRepository comicRepository,
                            ComicPriceRepository comicPriceRepository,
                            ComicCharacterRepository comicCharacterRepository,
                            CharacterRepository characterRepository) {
        this.comicRepository = comicRepository;
        this.comicPriceRepository = comicPriceRepository;
        this.comicCharacterRepository = comicCharacterRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public Comic findById(Long id) {
        return comicRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Comic save(ComicDtoRequest comicDtoRequest) {
        //TODO если нет id
        List<Character> idCharacterList = characterRepository.findAllById(comicDtoRequest.getCharactersIds());
        if(idCharacterList.isEmpty()){
            throw new RuntimeException("");
        }

        Comic comic = comicDtoRequest.toComicFromDto(comicDtoRequest);
        comicRepository.save(comic);

        List<ComicPrice> comicPrices = comicDtoRequest.getPrices()
                .stream()
                .map(c -> c.toComicPriceFromDto(c, comic))
                .collect(Collectors.toList());

        List<ComicCharacter> comicCharacter = comicDtoRequest.toComicCharacterList(comicDtoRequest, comic);

        comicPriceRepository.saveAll(comicPrices);
        comicCharacterRepository.saveAll(comicCharacter);
        return comic;
    }


    @Override
    public Comic update(ComicDtoRequest comicDtoRequest, Long id) {
        Comic comicFind = comicRepository.findById(id).orElseThrow(
                () -> new RuntimeException());

        Comic comic = comicDtoRequest.toComicFromDto(comicDtoRequest);

        comicFind.setTitle(comic.getTitle());
        comicFind.setDescription(comic.getDescription());
        comicFind.setFormat(comic.getFormat());
        comicFind.setPageCount(comic.getPageCount());
        comicFind.setText(comic.getText());
        comicFind.setResourceUri(comic.getResourceUri());

        return comicRepository.save(comicFind);
    }

    @Override
    public List<Comic> findAll() {
        return comicRepository.findAll();
    }
}

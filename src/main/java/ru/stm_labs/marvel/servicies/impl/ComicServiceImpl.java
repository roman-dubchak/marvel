package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.entities.*;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.repositories.*;
import ru.stm_labs.marvel.servicies.ComicService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl implements ComicService {

    private static final String FOLDER_ENTITY = "/comic/";

    private final ComicRepository comicRepository;
    private final ComicPriceRepository comicPriceRepository;
    private final ComicCharacterRepository comicCharacterRepository;
    private final CharacterRepository characterRepository;
    private final ImageComicRepository imageComicRepository;
    private final ImageComicServiceImpl imagesFileService;

    public ComicServiceImpl(ComicRepository comicRepository,
                            ComicPriceRepository comicPriceRepository,
                            ComicCharacterRepository comicCharacterRepository,
                            CharacterRepository characterRepository,
                            ImageComicRepository imageComicRepository,
                            ImageComicServiceImpl imagesFileService) {
        this.comicRepository = comicRepository;
        this.comicPriceRepository = comicPriceRepository;
        this.comicCharacterRepository = comicCharacterRepository;
        this.characterRepository = characterRepository;
        this.imageComicRepository = imageComicRepository;
        this.imagesFileService = imagesFileService;
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

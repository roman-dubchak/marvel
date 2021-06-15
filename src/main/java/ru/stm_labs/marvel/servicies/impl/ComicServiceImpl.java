package ru.stm_labs.marvel.servicies.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.dto.ComicDtoResponse;
import ru.stm_labs.marvel.dto.FilterComicDto;
import ru.stm_labs.marvel.dto.page.CreatePageData;
import ru.stm_labs.marvel.dto.page.PageData;
import ru.stm_labs.marvel.dto.page.PageDto;
import ru.stm_labs.marvel.dto.page.PageDtoCreator;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.handlerException.CharacterNotFoundException;
import ru.stm_labs.marvel.handlerException.ComicNotFoundException;
import ru.stm_labs.marvel.repositories.CharacterRepository;
import ru.stm_labs.marvel.repositories.ComicCharacterRepository;
import ru.stm_labs.marvel.repositories.ComicPriceRepository;
import ru.stm_labs.marvel.repositories.ComicRepository;
import ru.stm_labs.marvel.repositories.specification.ComicSpecification;
import ru.stm_labs.marvel.servicies.ComicService;

import java.util.List;
import java.util.function.Function;
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
        return comicRepository.findById(id).orElseThrow(
                () -> new ComicNotFoundException(String.format("Комикc с id %s не найден!", id)));
    }

    @Transactional
    @Override
    public Comic save(ComicDtoRequest comicDtoRequest) {
        List<Character> idCharacterList = characterRepository.findAllById(comicDtoRequest.getCharactersIds());
        if (idCharacterList.isEmpty()) {
            throw new CharacterNotFoundException("Ни один комикс не найден!");
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
                () -> new ComicNotFoundException(String.format("Комикc с id %s не найден!", id)));
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
    public PageDto<ComicDtoResponse> findAll(FilterComicDto filterComicDto,
                                             int page, int size, Sort sort) {
        PageData pageData = CreatePageData.createPageData(page, size, sort);
        BooleanExpression predicate = ComicSpecification.createPredicate(
                filterComicDto.getIdCharacter(),
                filterComicDto.getTitle(),
                filterComicDto.getDescription(),
                filterComicDto.getFormat(),
                filterComicDto.getPageCount(),
                filterComicDto.getText(),
                filterComicDto.getResourceUri(),
                filterComicDto.getPrices(),
                filterComicDto.getModified()
        );

        Page<Comic> comicPage = comicRepository.findAll(predicate, pageData);
        Function<Comic, ComicDtoResponse> convert = Comic -> toComicDtoResponse(Comic);
        return PageDtoCreator.createReadQueryResult(comicPage, convert);
    }

    private ComicDtoResponse toComicDtoResponse(Comic comic) {
        return new ComicDtoResponse().toComicDtoResponse(comic);
    }
}

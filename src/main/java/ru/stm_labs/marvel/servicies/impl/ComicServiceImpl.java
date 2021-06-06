package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.dto.mapper.ComicMapper;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.repositories.ComicRepository;
import ru.stm_labs.marvel.servicies.ComicService;

import java.util.List;
import java.util.Optional;

@Service
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;

    public ComicServiceImpl(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public Comic findById(Long id) {
        return comicRepository.findById(id).get();
    }

    @Override
    public Comic save(ComicDtoRequest comicDtoRequest) {
        Comic comic = ComicMapper.MAPPER.toComic(comicDtoRequest);
        return comicRepository.save(comic);
    }

    @Override
    public Comic update(ComicDtoRequest comicDtoRequest, Long id) {
        Comic comicFind = comicRepository.findById(id).orElseThrow(
                () -> new RuntimeException());

        Comic comic = ComicMapper.MAPPER.toComic(comicDtoRequest);
//        comicFind = ComicMapper.MAPPER.toComic(comic);
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

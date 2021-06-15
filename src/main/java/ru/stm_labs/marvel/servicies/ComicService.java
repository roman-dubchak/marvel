package ru.stm_labs.marvel.servicies;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.dto.ComicDtoRequest;
import ru.stm_labs.marvel.dto.ComicDtoResponse;
import ru.stm_labs.marvel.dto.FilterComicDto;
import ru.stm_labs.marvel.dto.page.PageDto;
import ru.stm_labs.marvel.entities.Comic;

@Service
public interface ComicService {

    Comic findById(Long id);

    Comic save(ComicDtoRequest comicDtoRequest);

    Comic update(ComicDtoRequest ComicDtoRequest, Long id);

    PageDto<ComicDtoResponse> findAll(FilterComicDto filterComicDto,
                                      int page, int size, Sort sort);

}

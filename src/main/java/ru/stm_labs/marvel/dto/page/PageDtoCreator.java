package ru.stm_labs.marvel.dto.page;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageDtoCreator {

    public static <Entity, DTO> PageDto<DTO> createReadQueryResult(
            Page<Entity> page, Function<Entity, DTO> convert) {
        List<DTO> dtoList = page.
                stream()
                .map(convert)
                .collect(Collectors.toList());
        return new PageDto<>(page.getTotalPages(), dtoList);
    }

    private PageDtoCreator() {
    }
}

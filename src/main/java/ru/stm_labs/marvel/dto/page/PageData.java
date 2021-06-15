package ru.stm_labs.marvel.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public class PageData {

    private final Pageable pageable;

    private final Sort sort;

}

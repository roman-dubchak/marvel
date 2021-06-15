package ru.stm_labs.marvel.dto.page;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class CreatePageData {

    public static PageData createPageData(int page, int size, Sort sort) {
        Pageable pageable = createPageable(page, size, sort);
        return new PageData(pageable, sort);
    }

    public static Pageable createPageable(int page, int size, Sort sort) {
        if (page < 0 || size <= 0) {
            return Pageable.unpaged();
        }
        return PageRequest.of(page, size, sort);
    }

}

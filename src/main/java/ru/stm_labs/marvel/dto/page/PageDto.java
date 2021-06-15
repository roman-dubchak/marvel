package ru.stm_labs.marvel.dto.page;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {

    int totalPage;

    List<T> content;

}

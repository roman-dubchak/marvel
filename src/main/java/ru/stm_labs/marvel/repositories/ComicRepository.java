package ru.stm_labs.marvel.repositories;

import org.springframework.stereotype.Repository;
import ru.stm_labs.marvel.entities.Comic;

@Repository
public interface ComicRepository extends BaseRepository<Comic> {
}

package ru.stm_labs.marvel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stm_labs.marvel.entities.ComicCharacter;
import ru.stm_labs.marvel.entities.ComicCharacterId;

@Repository
public interface ComicCharacterRepository extends JpaRepository<ComicCharacter, ComicCharacterId> {
}

package ru.stm_labs.marvel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stm_labs.marvel.entities.ImageCharacter;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageCharacterRepository extends JpaRepository<ImageCharacter, Long> {
}
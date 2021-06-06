package ru.stm_labs.marvel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stm_labs.marvel.entities.ComicPrice;

@Repository
public interface ComicPriceRepository extends JpaRepository<ComicPrice, Long> {
}

package ru.stm_labs.marvel.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ComicCharacterId implements Serializable {

    @Column(name = "comic_id")
    private Long comicId;

    @Column(name = "character_id")
    private Long characterId;


}

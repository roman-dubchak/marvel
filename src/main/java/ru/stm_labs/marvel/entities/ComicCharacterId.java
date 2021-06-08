package ru.stm_labs.marvel.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class ComicCharacterId implements Serializable {

    @Column(name = "comic_id")
    private Long comicId;

    @Column(name = "character_id")
    private Long characterId;


}

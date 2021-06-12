package ru.stm_labs.marvel.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comic_character")
public class ComicCharacter {

    @EmbeddedId
    private ComicCharacterId comicCharacterId;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "modified")
    @CreationTimestamp
    private LocalDateTime modified;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active = true;

//    @PostConstruct
//    private void init(){
//        this.active = true;
//    }

}

package ru.stm_labs.marvel.entities;

import liquibase.pro.packaged.W;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "character_comic")
public class ComicCharacter{

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

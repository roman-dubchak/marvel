package ru.stm_labs.marvel.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image_character")
public class ImageCharacter extends BaseEntity {

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character characterId;
}

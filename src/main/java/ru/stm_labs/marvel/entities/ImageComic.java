package ru.stm_labs.marvel.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image_comic")
public class ImageComic extends BaseEntity {

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comicId;
}

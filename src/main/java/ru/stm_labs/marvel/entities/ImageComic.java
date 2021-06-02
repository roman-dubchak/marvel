package ru.stm_labs.marvel.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table (name ="image_comic")
public class ImageComic extends BaseEntity{

    @Column(name = "path")
    private String path;

}

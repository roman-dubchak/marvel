package ru.stm_labs.marvel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "character")
public class Character extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "resource_uri")
    private String resourceUri;

    @OneToMany(mappedBy = "characterId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageCharacter> images;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "comic_character",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private List<Comic> comics;

    public void addImages(ImageCharacter imageCharacter) {
        if (imageCharacter == null) {
            images = new ArrayList<>();
        }
        images.add(imageCharacter);
    }

}

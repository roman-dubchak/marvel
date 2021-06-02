package ru.stm_labs.marvel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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
    @JoinTable(
            name = "character_comic",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic-id"))
    private List<Comic> comics;

}

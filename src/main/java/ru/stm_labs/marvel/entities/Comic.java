package ru.stm_labs.marvel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "comic")
public class Comic extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "format")
    private String format;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "text")
    private String text;

    @Column(name = "resource_uri")
    private String resourceUri;

    @OneToMany(mappedBy = "comicId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComicPrice> prices;

    @OneToMany(mappedBy = "comicId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageComic> images;

    @ManyToMany
    @JoinTable(
            name = "character_comic",
            joinColumns = @JoinColumn(name = "comic-id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Character> characters;

    public void addImages(ImageComic imageComic) {
        if (imageComic == null) {
            images = new ArrayList<>();
        }
        images.add(imageComic);
    }

}

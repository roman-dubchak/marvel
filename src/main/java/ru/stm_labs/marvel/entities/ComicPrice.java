package ru.stm_labs.marvel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comic_price")
public class ComicPrice extends BaseEntity {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeComic typeComic;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comicId;
}

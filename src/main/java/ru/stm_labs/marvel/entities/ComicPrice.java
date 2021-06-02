package ru.stm_labs.marvel.entities;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comic_price")
public class ComicPrice extends BaseEntity {

    @Getter
    enum TypeComic {

        PRINT(1),
        DIGITAL(2);

        private int typeComic;

        TypeComic(int typeComic) {
        }
    }

    @Column(name = "type")
    private TypeComic typeComic;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "comic_id")
    private Comic comicId;
}

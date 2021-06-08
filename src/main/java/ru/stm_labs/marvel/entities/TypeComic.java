package ru.stm_labs.marvel.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TypeComic {

        PRINT("PRINT"),
        DIGITAL("DIGITAL");

        private String typeComic;

        TypeComic(String typeComic) {
            this.typeComic = typeComic;
        }

        public static TypeComic typeComic (String typeComic){
            for(TypeComic t : values()){
                if(t.typeComic.equalsIgnoreCase(typeComic))
                    return t;
            }
            throw new IllegalArgumentException("Unknown enum type " + typeComic + ", " +
                    "Allowed values are " + Arrays.toString(values()));
        }

}

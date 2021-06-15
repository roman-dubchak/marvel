package ru.stm_labs.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterCharacterDto {

    private Long idComic;

    @NotBlank(message = "Поле name не должно быть пустым!")
    @NotNull(message = "Поле name не должно быть пустым!")
    private String name;

    @NotBlank(message = "Поле description не должно быть пустым!")
    @NotNull(message = "Поле description не должно быть пустым!")
    private String description;

    @NotBlank(message = "Поле resourceUri не должно быть пустым!")
    @NotNull(message = "Поле resourceUri не должно быть пустым!")
    private String resourceUri;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modified;

    public FilterCharacterDto(String name, String description, String resourceUri, LocalDateTime modified) {
        this.name = name;
        this.description = description;
        this.resourceUri = resourceUri;
        this.modified = modified;
    }
}

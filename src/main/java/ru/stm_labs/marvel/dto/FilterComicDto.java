package ru.stm_labs.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterComicDto {

    private Long idCharacter;

    @NotBlank(message = "Поле title не должно быть пустым!")
    @NotNull(message = "Поле title не должно быть пустым!")
    private String title;

    @NotNull(message = "Поле description не должно быть пустым!")
    @NotEmpty(message = "Поле description не должно быть пустым!")
    private String description;

    @NotNull(message = "Поле format не должно быть пустым!")
    @NotEmpty(message = "Поле format не должно быть пустым!")
    private String format;

    @Min(value = 1, message = "Минимальное значение pages = 1")
    private Integer pageCount;

    @NotNull(message = "Поле text не должно быть пустым!")
    @NotEmpty(message = "Поле text не должно быть пустым!")
    private String text;

    @NotNull(message = "Поле url не должно быть пустым!")
    @NotEmpty(message = "Поле url не должно быть пустым!")
    private String resourceUri;

    @NotNull(message = "Поле prices не должно быть пустым!")
    @NotEmpty(message = "Поле prices не должно быть пустым!")
    private Double prices;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modified;

    public FilterComicDto(String title, String description, String format, Integer pageCount, String text, String resourceUri, Double prices, LocalDateTime modified) {
        this.title = title;
        this.description = description;
        this.format = format;
        this.pageCount = pageCount;
        this.text = text;
        this.resourceUri = resourceUri;
        this.prices = prices;
        this.modified = modified;
    }
}

package ru.stm_labs.marvel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ComicPrice;
import ru.stm_labs.marvel.entities.ImageCharacter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDtoResponse {

    private String name;

    private String description;

    private String resourceUri;

    private List<ImageCharacterDto> images;

    private List<ComicDtoResponse> comics;

    private LocalDateTime modified;

    public CharacterDtoResponse toCharacterDtoResponse(Character character) {
        CharacterDtoResponse characterDtoResponse = new CharacterDtoResponse();
        characterDtoResponse.setName(character.getName());
        characterDtoResponse.setDescription(character.getDescription());
        characterDtoResponse.setResourceUri(character.getResourceUri());
        characterDtoResponse.setImages(imageCharacterDtoList(character.getImages()));
        characterDtoResponse.setComics(toComicResponseDtoList(character.getComics()));
        characterDtoResponse.setModified(character.getModified());
        return characterDtoResponse;
    }

    private List<ImageCharacterDto> imageCharacterDtoList(List<ImageCharacter> imageCharacters) {
        return imageCharacters
                .stream()
                .map(i -> toImageCharacterDto(i))
                .collect(Collectors.toList());
    }

    private ImageCharacterDto toImageCharacterDto(ImageCharacter imageCharacter) {
        ImageCharacterDto imageCharacterDto = new ImageCharacterDto();
        imageCharacterDto.setPath(imageCharacter.getPath());

        return imageCharacterDto;
    }

    private ComicDtoResponse toComicDtoResponseDto(Comic comic) {
        ComicDtoResponse comicDtoResponse = new ComicDtoResponse();
        comicDtoResponse.setTitle(comic.getTitle());
        comicDtoResponse.setDescription(comic.getDescription());
        comicDtoResponse.setFormat(comic.getFormat());
        comicDtoResponse.setPageCount(comic.getPageCount());
        comicDtoResponse.setPageCount(comic.getPageCount());
        comicDtoResponse.setText(comic.getText());
        comicDtoResponse.setResourceUri(comic.getResourceUri());
        comicDtoResponse.setPrices(toComicPriceDtoResponseList(comic.getPrices()));
        return comicDtoResponse;
    }

    private List<ComicDtoResponse> toComicResponseDtoList(List<Comic> comics) {
        return comics
                .stream()
                .map(c -> toComicDtoResponseDto(c))
                .collect(Collectors.toList());
    }

    private ComicPriceDtoRequest toComicPriceDtoResponse(ComicPrice comicPrice) {
        ComicPriceDtoRequest comicPriceDtoRequest = new ComicPriceDtoRequest();
        comicPriceDtoRequest.setTypeComic(comicPrice.getTypeComic().getTypeComic());
        comicPriceDtoRequest.setPrice(comicPrice.getPrice());
        return comicPriceDtoRequest;
    }

    private List<ComicPriceDtoRequest> toComicPriceDtoResponseList(List<ComicPrice> comicPriceList) {
        return comicPriceList
                .stream()
                .map(c -> (toComicPriceDtoResponse(c)))
                .collect(Collectors.toList());
    }

}

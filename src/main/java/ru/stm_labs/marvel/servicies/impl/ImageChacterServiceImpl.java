package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.entities.Character;
import ru.stm_labs.marvel.entities.ImageCharacter;
import ru.stm_labs.marvel.repositories.ImageCharacterRepository;
import ru.stm_labs.marvel.servicies.ImageCharacterService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageChacterServiceImpl implements ImageCharacterService {
    private static final String FOLDER_NAME = "/images/character/";

    private final ImageCharacterRepository imageCharacterRepository;

    public ImageChacterServiceImpl(ImageCharacterRepository imageCharacterRepository) {
        this.imageCharacterRepository = imageCharacterRepository;
    }

    private Path saveFileInDisk(Character character, MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        Path path = null;
        try {
            path = Paths.get(FOLDER_NAME + character.getName() + "/" + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public ImageCharacter save(Character character, MultipartFile file) {

        Path path = saveFileInDisk(character, file);

        ImageCharacter imageCharacter = new ImageCharacter();
        imageCharacter.setCharacterId(character);
        imageCharacter.setPath(path.toString());

        character.addImages(imageCharacter);

        imageCharacterRepository.save(imageCharacter);
        return imageCharacter;
    }
}

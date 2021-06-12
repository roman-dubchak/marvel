package ru.stm_labs.marvel.servicies;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ImageComic;
import ru.stm_labs.marvel.repositories.ImageComicRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagesFileService {
    private static final String FOLDER_NAME = "/images";

    private final ImageComicRepository imageComicRepository;

    public ImagesFileService(ImageComicRepository imageComicRepository) {
        this.imageComicRepository = imageComicRepository;
    }

    private Path saveFileInDisk(MultipartFile file, String folderEntity) {
        if (file.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        Path path = null;
        try {
            path = Paths.get(FOLDER_NAME + folderEntity + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void saveImageComicInRepository(Comic comic, MultipartFile file, String folderEntity) {
        ImageComic imageComic = new ImageComic();
        imageComic.setComicId(comic);
        Path path = saveFileInDisk(file, folderEntity);
        imageComic.setPath(path.toString());
        imageComicRepository.save(imageComic);
    }
}

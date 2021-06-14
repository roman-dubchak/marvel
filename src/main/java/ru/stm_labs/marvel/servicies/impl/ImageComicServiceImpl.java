package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stm_labs.marvel.entities.Comic;
import ru.stm_labs.marvel.entities.ImageComic;
import ru.stm_labs.marvel.repositories.ImageComicRepository;
import ru.stm_labs.marvel.servicies.ImageComicService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageComicServiceImpl implements ImageComicService {

    private static final String FOLDER_NAME = "/images/comic/";

    private final ImageComicRepository imageComicRepository;

    public ImageComicServiceImpl(ImageComicRepository imageComicRepository) {
        this.imageComicRepository = imageComicRepository;
    }

    private Path saveFileInDisk(Comic comic, MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        Path path = null;
        try {
            path = Paths.get(FOLDER_NAME + comic.getTitle() + "/" + fileName);
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public ImageComic save(Comic comic, MultipartFile file) {
        Path path = saveFileInDisk(comic, file);

        ImageComic imageComic = new ImageComic();
        imageComic.setComicId(comic);
        imageComic.setPath(path.toString());

        comic.addImages(imageComic);

        imageComicRepository.save(imageComic);
        return imageComic;
    }
}

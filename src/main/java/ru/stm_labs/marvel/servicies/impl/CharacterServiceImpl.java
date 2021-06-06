package ru.stm_labs.marvel.servicies.impl;

import org.springframework.stereotype.Service;
import ru.stm_labs.marvel.dto.CharacterDtoRequest;
import ru.stm_labs.marvel.dto.mapper.CharacterMapper;
import ru.stm_labs.marvel.repositories.CharacterRepository;
import ru.stm_labs.marvel.servicies.CharacterService;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character findById(Long id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public Character save(CharacterDtoRequest characterDtoRequest) {
        Character character = CharacterMapper.MAPPER.toCharacter(characterDtoRequest);
        return characterRepository.save(character);
    }

    @Override
    public Character update(CharacterDtoRequest characterDtoRequest, Long id) {
        Character characterUpd = characterRepository.findById(id).orElseThrow(
                () -> new RuntimeException());

        Character character = CharacterMapper.MAPPER.toCharacter(characterDtoRequest);
        characterUpd = CharacterMapper.MAPPER.toCharacter(character);
        //TODO set fields

        return characterRepository.save(characterUpd);
    }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }
}

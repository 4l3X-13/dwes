package daw2.dwes.morty1.service;

import daw2.dwes.morty1.repository.CharacterRepository;
import daw2.dwes.morty1.model.Character;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<Character> findAll() {
        return repository.findAll();
    }

    public Character findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}



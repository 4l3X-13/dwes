package daw2.dwes.morty1.service;

import daw2.dwes.morty1.model.Episode;
import daw2.dwes.morty1.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    private final EpisodeRepository repository;

    public EpisodeService(EpisodeRepository repository) {
        this.repository = repository;
    }

    public List<Episode> findAll() {
        return repository.findAll();
    }

    public Episode findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}


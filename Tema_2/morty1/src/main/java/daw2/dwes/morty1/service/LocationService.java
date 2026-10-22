package daw2.dwes.morty1.service;

import daw2.dwes.morty1.model.Location;
import daw2.dwes.morty1.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}


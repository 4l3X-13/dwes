package daw2.dwes.morty1.repository;

import daw2.dwes.morty1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}


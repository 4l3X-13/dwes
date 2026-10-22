package daw2.dwes.morty1.repository;

import daw2.dwes.morty1.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
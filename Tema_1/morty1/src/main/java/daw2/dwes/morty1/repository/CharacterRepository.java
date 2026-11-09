package daw2.dwes.morty1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import daw2.dwes.morty1.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    // Aquí puedes añadir consultas personalizadas si lo necesitas, por defecto se crean
    //
}


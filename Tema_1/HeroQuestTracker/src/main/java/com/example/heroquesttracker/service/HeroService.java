package com.example.heroquesttracker.service;

import com.example.heroquesttracker.model.Hero;
import com.example.heroquesttracker.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    /**
     * Crea los héroes iniciales de nuestro mundo si no existen todavía.
     */
    public void initializeHeroes() {
        List<Hero> defaultHeroes = List.of(
                new Hero("Conan", "Bárbaro", 1, 0.0f, 50),
                new Hero("Gandalf", "Mago", 1, 0.0f, 30),
                new Hero("Legolas", "Arquero", 1, 0.0f, 40),
                new Hero("Gimli", "Guerrero Enano", 1, 0.0f, 60)
        );

        for (Hero hero : defaultHeroes) {
            if (!heroRepository.existsById(hero.getName())) {
                heroRepository.save(hero);
            }
        }
    }

    /**
     * Devuelve un listado con todos los héroes.
     */
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    /**
     * Devuelve un héroe cuyo nombre coincide con el parámetro pasado.
     */
    public Hero getHero(String name) {
        return heroRepository.findById(name).orElse(null);
    }

    /**
     * Actualiza el héroe sumando experiencia.
     * Cada 10 puntos de experiencia sumados, el héroe sube un nivel.
     */
    public Hero addExperience(Hero hero, float xp) {
        if (hero != null) {
            hero.addExperience(xp);
            return heroRepository.save(hero);
        }
        return null;
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }
}

package com.example.heroquesttracker.service;

import com.example.heroquesttracker.model.Hero;
import com.example.heroquesttracker.model.Quest;
import com.example.heroquesttracker.repository.HeroRepository;
import com.example.heroquesttracker.repository.QuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestService {

    private final QuestRepository questRepository;
    private final HeroRepository heroRepository;
    private final HeroService heroService;

    private static final List<String> adjectives = List.of(
            "Lost", "Forbidden", "Ancient", "Cursed",
            "Hidden", "Glorious", "Frozen", "Burning"
    );

    private static final List<String> nouns = List.of(
            "Temple", "Blade", "Forest", "Scroll", "Beast",
            "Crown", "Crypt", "Dragon"
    );

    private static final List<String> actions = List.of(
            "Rescue", "Defeat", "Recover", "Explore",
            "Protect", "Destroy", "Uncover", "Seal"
    );

    private static final List<String> rewards = List.of(
            "Poción curativa", "Gema de rubí", "Amuleto de fuego",
            "Escudo de plata", "Anillo de maná", "Daga élfica"
    );

    private static final Random random = new Random();

    public QuestService(QuestRepository questRepository, HeroRepository heroRepository, HeroService heroService) {
        this.questRepository = questRepository;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
    }

    private static String generateTitle() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));
        String action = actions.get(random.nextInt(actions.size()));
        return action + " the " + adjective + " " + noun;
    }

    /**
     * Crea una misión de manera aleatoria.
     * Tendrá un nombre aleatorio y valores de experiencia entre 0 y 10 (con un decimal).
     * Se asigna aleatoriamente a uno de los héroes.
     */
    public Quest createRandomQuest() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            heroService.initializeHeroes();
            heroes = heroRepository.findAll();
        }

        if (heroes.isEmpty()) {
            return null;
        }

        Hero randomHero = heroes.get(random.nextInt(heroes.size()));

        // Valores de experiencia entre 0 y 10 (con un decimal)
        float xp = (random.nextInt(101)) / 10.0f; // e.g. 0.0, 0.1, ..., 10.0

        // Mejora: Recompensa en oro y objeto
        int rewardGold = (random.nextInt(10) + 1) * 10; // 10 a 100 oro
        String rewardItem = rewards.get(random.nextInt(rewards.size()));

        Quest quest = new Quest();
        quest.setTitle(generateTitle());
        quest.setSolved(false);
        quest.setExperience(xp);
        quest.setRewardGold(rewardGold);
        quest.setRewardItem(rewardItem);
        quest.setHero(randomHero);

        return questRepository.save(quest);
    }

    /**
     * Devuelve un listado con todas las misiones.
     */
    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    /**
     * Devuelve una misión por su ID.
     */
    public Quest getQuest(Long id) {
        if (id == null) return null;
        return questRepository.findById(id).orElse(null);
    }

    /**
     * Actualiza una misión y la marca como resuelta.
     * Además sumará experiencia (y oro) al héroe asignado.
     */
    public Quest markQuestAsSolved(Long id) {
        if (id == null) return null;

        Quest quest = questRepository.findById(id).orElse(null);
        if (quest != null && !Boolean.TRUE.equals(quest.getSolved())) {
            quest.setSolved(true);
            Hero hero = quest.getHero();
            if (hero != null) {
                hero.addExperience(quest.getExperience());
                hero.addGold(quest.getRewardGold());
                heroRepository.save(hero);
            }
            return questRepository.save(quest);
        }
        return quest;
    }

    /**
     * Borra la misión con el ID indicado.
     */
    public void failQuest(Long id) {
        if (id != null && questRepository.existsById(id)) {
            questRepository.deleteById(id);
        }
    }

    /**
     * Sobrecarga sin parámetros para borrar la última misión o misión pendiente.
     */
    public void failQuest() {
        List<Quest> quests = questRepository.findAll();
        if (!quests.isEmpty()) {
            questRepository.delete(quests.get(quests.size() - 1));
        }
    }
}

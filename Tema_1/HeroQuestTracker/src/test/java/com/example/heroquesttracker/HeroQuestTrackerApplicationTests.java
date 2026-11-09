package com.example.heroquesttracker;

import com.example.heroquesttracker.controller.WorldController;
import com.example.heroquesttracker.model.Hero;
import com.example.heroquesttracker.model.Quest;
import com.example.heroquesttracker.repository.HeroRepository;
import com.example.heroquesttracker.repository.QuestRepository;
import com.example.heroquesttracker.service.HeroService;
import com.example.heroquesttracker.service.QuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HeroQuestTrackerApplicationTests {

    private HeroRepository heroRepository;
    private QuestRepository questRepository;
    private HeroService heroService;
    private QuestService questService;
    private WorldController worldController;

    @BeforeEach
    void setUp() {
        heroRepository = Mockito.mock(HeroRepository.class);
        questRepository = Mockito.mock(QuestRepository.class);
        heroService = new HeroService(heroRepository);
        questService = new QuestService(questRepository, heroRepository, heroService);
        worldController = new WorldController(heroService, questService);
    }

    @Test
    @DisplayName("Hero entity addExperience: levels up every 10 points")
    void testHeroLevelUpOnExperience() {
        Hero hero = new Hero("Conan", "Bárbaro", 1, 0.0f);
        assertEquals(1, hero.getNivel());
        assertEquals(0.0f, hero.getExperience());

        // Add 5.5 XP -> Still level 1
        hero.addExperience(5.5f);
        assertEquals(1, hero.getNivel());
        assertEquals(5.5f, hero.getExperience());

        // Add 4.5 XP -> Total 10.0 XP -> Level 2
        hero.addExperience(4.5f);
        assertEquals(2, hero.getNivel());
        assertEquals(10.0f, hero.getExperience());

        // Add 15.0 XP -> Total 25.0 XP -> Level 3
        hero.addExperience(15.0f);
        assertEquals(3, hero.getNivel());
        assertEquals(25.0f, hero.getExperience());
    }

    @Test
    @DisplayName("HeroService: initializeHeroes saves default heroes")
    void testInitializeHeroes() {
        when(heroRepository.existsById(anyString())).thenReturn(false);
        heroService.initializeHeroes();
        verify(heroRepository, atLeast(4)).save(any(Hero.class));
    }

    @Test
    @DisplayName("QuestService: createRandomQuest assigns to random hero")
    void testCreateRandomQuest() {
        Hero hero = new Hero("Gandalf", "Mago", 1, 0.0f);
        when(heroRepository.findAll()).thenReturn(List.of(hero));
        when(questRepository.save(any(Quest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Quest quest = questService.createRandomQuest();

        assertNotNull(quest);
        assertNotNull(quest.getTitle());
        assertFalse(quest.getSolved());
        assertTrue(quest.getExperience() >= 0.0f && quest.getExperience() <= 10.0f);
        assertEquals("Gandalf", quest.getHero().getName());
        verify(questRepository, times(1)).save(any(Quest.class));
    }

    @Test
    @DisplayName("QuestService: markQuestAsSolved marks solved and adds XP to hero")
    void testMarkQuestAsSolved() {
        Hero hero = new Hero("Legolas", "Arquero", 1, 8.0f);
        Quest quest = new Quest();
        quest.setId(1L);
        quest.setTitle("Defeat the Dragon");
        quest.setSolved(false);
        quest.setExperience(3.5f);
        quest.setRewardGold(50);
        quest.setHero(hero);

        when(questRepository.findById(1L)).thenReturn(Optional.of(quest));
        when(questRepository.save(any(Quest.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Quest solvedQuest = questService.markQuestAsSolved(1L);

        assertTrue(solvedQuest.getSolved());
        assertEquals(2, hero.getNivel()); // 8.0 + 3.5 = 11.5 XP -> Level 2
        assertEquals(11.5f, hero.getExperience());
        assertEquals(50, hero.getGold());
        verify(heroRepository, times(1)).save(hero);
        verify(questRepository, times(1)).save(quest);
    }

    @Test
    @DisplayName("QuestService: failQuest deletes quest by ID")
    void testFailQuest() {
        when(questRepository.existsById(10L)).thenReturn(true);
        questService.failQuest(10L);
        verify(questRepository, times(1)).deleteById(10L);
    }

    @Test
    @DisplayName("WorldController: routes return expected views and redirects")
    void testWorldControllerRoutes() {
        Model model = new ConcurrentModel();

        // /heroes/initialize -> redirect:/heroes
        String initResult = worldController.initializeHeroes();
        assertEquals("redirect:/heroes", initResult);

        // /heroes -> hero-list
        when(heroRepository.findAll()).thenReturn(new ArrayList<>());
        String heroesResult = worldController.getAllHeroes(model);
        assertEquals("hero-list", heroesResult);
        assertTrue(model.containsAttribute("heroes"));

        // /quests -> quest-list
        when(questRepository.findAll()).thenReturn(new ArrayList<>());
        String questsResult = worldController.getAllQuests(model);
        assertEquals("quest-list", questsResult);
        assertTrue(model.containsAttribute("quests"));

        // /quests/new -> redirect:/quests
        when(heroRepository.findAll()).thenReturn(List.of(new Hero("Conan", "Bárbaro", 1, 0.0f)));
        when(questRepository.save(any(Quest.class))).thenAnswer(inv -> inv.getArgument(0));
        String newQuestResult = worldController.newQuest();
        assertEquals("redirect:/quests", newQuestResult);

        // /heroes/{name} -> hero-detail
        Hero gimli = new Hero("Gimli", "Enano", 1, 0.0f);
        when(heroRepository.findById("Gimli")).thenReturn(Optional.of(gimli));
        String detailResult = worldController.getHeroDetail("Gimli", model);
        assertEquals("hero-detail", detailResult);
        assertEquals(gimli, model.getAttribute("hero"));

        // /quests/solve/ID -> redirect:/quests
        String solveResult = worldController.solveQuest(1L);
        assertEquals("redirect:/quests", solveResult);

        // /quests/fail/ID -> redirect:/quests
        String failResult = worldController.failQuest(1L);
        assertEquals("redirect:/quests", failResult);
    }
}

package com.example.heroquesttracker.controller;

import com.example.heroquesttracker.model.Hero;
import com.example.heroquesttracker.service.HeroService;
import com.example.heroquesttracker.service.QuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WorldController {

    private final HeroService heroService;
    private final QuestService questService;

    public WorldController(HeroService heroService, QuestService questService) {
        this.heroService = heroService;
        this.questService = questService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/heroes";
    }

    /**
     * /heroes/initialize - Crea los héroes de nuestro mundo y a continuación muestra el listado de héroes.
     */
    @GetMapping("/heroes/initialize")
    public String initializeHeroes() {
        heroService.initializeHeroes();
        return "redirect:/heroes";
    }

    /**
     * /heroes - Muestra un listado con todos los héroes.
     */
    @GetMapping("/heroes")
    public String getAllHeroes(Model model) {
        List<Hero> heroes = heroService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        return "hero-list";
    }

    /**
     * /quests - Muestra un listado con todas las misiones.
     */
    @GetMapping("/quests")
    public String getAllQuests(Model model) {
        model.addAttribute("quests", questService.getAllQuests());
        return "quest-list";
    }

    /**
     * /quests/new - Crea una nueva misión aleatoriamente, la asigna a un héroe existente
     * y después muestra un listado con todas las misiones.
     */
    @GetMapping("/quests/new")
    public String newQuest() {
        questService.createRandomQuest();
        return "redirect:/quests";
    }

    /**
     * /heroes/NAME - Muestra el detalle del héroe con ese nombre. Mostrará también las misiones asociadas.
     */
    @GetMapping("/heroes/{name}")
    public String getHeroDetail(@PathVariable("name") String name, Model model) {
        Hero hero = heroService.getHero(name);
        if (hero == null) {
            return "redirect:/heroes";
        }
        model.addAttribute("hero", hero);
        model.addAttribute("quests", hero.getQuests());
        return "hero-detail";
    }

    /**
     * /quests/solve/ID - Marca como resuelta la misión con ese ID.
     */
    @GetMapping("/quests/solve/{id}")
    public String solveQuest(@PathVariable("id") Long id) {
        questService.markQuestAsSolved(id);
        return "redirect:/quests";
    }

    /**
     * /quests/fail/ID - Borra la misión con ese ID.
     */
    @GetMapping("/quests/fail/{id}")
    public String failQuest(@PathVariable("id") Long id) {
        questService.failQuest(id);
        return "redirect:/quests";
    }
}

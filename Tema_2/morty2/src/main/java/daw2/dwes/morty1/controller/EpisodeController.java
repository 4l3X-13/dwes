package daw2.dwes.morty1.controller;

import daw2.dwes.morty1.model.Episode;
import daw2.dwes.morty1.service.EpisodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping
    public String listEpisodes(Model model) {
        model.addAttribute("episodes", episodeService.findAll());
        return "episodes/list";
    }

    @GetMapping("/create")
    public String createEpisode() {
        Episode episode = new Episode();
        episode.setName("Funciona");
        episode.setAirDate(LocalDate.now());
        episode.setEpisodeCode("S10E01");

        episodeService.save(episode);

        return "redirect:/episodes";
    }
}
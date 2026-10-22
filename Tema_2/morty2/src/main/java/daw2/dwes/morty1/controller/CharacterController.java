package daw2.dwes.morty1.controller;

import daw2.dwes.morty1.model.Character;
import daw2.dwes.morty1.model.Gender;
import daw2.dwes.morty1.model.Location;
import daw2.dwes.morty1.repository.CharacterRepository;
import daw2.dwes.morty1.repository.LocationRepository;
import daw2.dwes.morty1.service.CharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService service;
    private final LocationRepository locationRepository;
    private final CharacterRepository characterRepository;

    public CharacterController(CharacterService service,
                               LocationRepository locationRepository,
                               CharacterRepository characterRepository) {
        this.service = service;
        this.locationRepository = locationRepository;
        this.characterRepository = characterRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("characters", service.findAll());
        return "characters/list";
    }


    @GetMapping("/list")
    public String listExplicit(Model model) {
        model.addAttribute("characters", service.findAll());
        return "characters/list";
    }

    @GetMapping("/create")
    public String createCharacterWithLocation() {
        Location nuevaLocation = new Location();
        nuevaLocation.setName("Citadel of Ricks");
        nuevaLocation.setDimension("Dimension C-137");
        locationRepository.save(nuevaLocation);

        Character nuevo = new Character();
        nuevo.setName("Bob");
        nuevo.setStatus("Alive");
        nuevo.setSpecies("Human");
        nuevo.setGender(Gender.Male);
        nuevo.setLocation(nuevaLocation);
        nuevo.setWeight(75.0f);
        nuevo.setHeight(1.85f);

        characterRepository.save(nuevo);

        return "redirect:/characters";
    }

    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/characters";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("character", service.findById(id));
        return "characters/detail";
    }
}
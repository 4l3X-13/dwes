package com.example.plantillasbasicas.controller;

import com.example.plantillasbasicas.model.Pelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 2.2.4. Home
 * Controlador en la ruta / con enlace a una película, al listado y a inicializar.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Pelicula> peliculas = InicializarController.peliculas;
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("totalPeliculas", peliculas.size());

        // Si hay películas disponibles, pasamos la primera para enlazar directamente
        if (!peliculas.isEmpty()) {
            model.addAttribute("primeraPelicula", peliculas.get(0));
            model.addAttribute("idPrimeraPelicula", 0);
        }

        return "home";
    }
}

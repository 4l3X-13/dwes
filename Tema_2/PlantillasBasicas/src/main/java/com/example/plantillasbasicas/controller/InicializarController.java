package com.example.plantillasbasicas.controller;

import com.example.plantillasbasicas.model.Pelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 2.2.1. Inicializar
 * Controlador que crea un listado de películas y lo guarda en un listado de películas.
 * Este listado de películas es accesible desde el resto de controladores.
 */
@Controller
public class InicializarController {

    // Listado en memoria accesible desde otros controladores
    public static final List<Pelicula> peliculas = new ArrayList<>();

    static {
        // Inicialización estática por defecto para garantizar datos disponibles
        cargarPeliculasPorDefecto();
    }

    public static void cargarPeliculasPorDefecto() {
        peliculas.clear();
        peliculas.add(new Pelicula(
                "The Matrix",
                "Ciencia Ficción",
                8.7,
                "https://www.imdb.com/title/tt0133093/",
                "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                LocalDate.of(1999, 3, 31)
        ));

        peliculas.add(new Pelicula(
                "Pulp Fiction",
                "Crimen",
                8.9,
                "https://www.imdb.com/title/tt0110912/",
                "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
                LocalDate.of(1994, 10, 14)
        ));

        peliculas.add(new Pelicula(
                "El Padrino",
                "Drama",
                9.2,
                "https://www.imdb.com/title/tt0068646/",
                "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                LocalDate.of(1972, 3, 24)
        ));

        peliculas.add(new Pelicula(
                "Interstellar",
                "Ciencia Ficción",
                8.7,
                "https://www.imdb.com/title/tt0816692/",
                "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                LocalDate.of(2014, 11, 7)
        ));

        peliculas.add(new Pelicula(
                "El Rey León",
                "Animación",
                8.5,
                "https://www.imdb.com/title/tt0110357/",
                "https://image.tmdb.org/t/p/w500/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg",
                LocalDate.of(1994, 6, 24)
        ));

        peliculas.add(new Pelicula(
                "El Señor de los Anillos: El Retorno del Rey",
                "Fantasía",
                9.0,
                "https://www.imdb.com/title/tt0167260/",
                "https://image.tmdb.org/t/p/w500/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg",
                LocalDate.of(2003, 12, 17)
        ));
    }

    @GetMapping("/inicializar")
    public String inicializar(Model model) {
        cargarPeliculasPorDefecto();
        model.addAttribute("totalPeliculas", peliculas.size());
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("mensaje", "¡Listado de películas inicializado con éxito!");
        return "inicializar";
    }
}

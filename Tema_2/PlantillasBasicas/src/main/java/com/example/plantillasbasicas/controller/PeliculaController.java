package com.example.plantillasbasicas.controller;

import com.example.plantillasbasicas.model.Pelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador para listar películas y ver el detalle de una película.
 * Accede al listado de películas de InicializarController.
 */
@Controller
public class PeliculaController {

    /**
     * 2.2.2. Listar películas
     * Muestra el listado de películas en una tabla en una plantilla Thymeleaf.
     */
    @GetMapping("/peliculas")
    public String listarPeliculas(Model model) {
        List<Pelicula> peliculas = InicializarController.peliculas;
        model.addAttribute("peliculas", peliculas);
        return "peliculas";
    }

    /**
     * 2.2.3. Ver película (con parámetro de ruta /pelicula/{id})
     * El parámetro 'id' representa la posición en el listado.
     */
    @GetMapping("/pelicula/{id}")
    public String verPeliculaPorRuta(@PathVariable("id") int id, Model model) {
        return cargarDetallePelicula(id, model);
    }

    /**
     * Soporte opcional para /pelicula?id=X
     */
    @GetMapping("/pelicula")
    public String verPeliculaPorParametro(@RequestParam(name = "id", defaultValue = "0") int id, Model model) {
        return cargarDetallePelicula(id, model);
    }

    private String cargarDetallePelicula(int id, Model model) {
        List<Pelicula> peliculas = InicializarController.peliculas;
        if (peliculas.isEmpty()) {
            return "redirect:/inicializar";
        }
        if (id < 0 || id >= peliculas.size()) {
            model.addAttribute("error", "No se ha encontrado ninguna película en la posición " + id);
            return "redirect:/peliculas";
        }

        Pelicula pelicula = peliculas.get(id);
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("posicion", id);
        model.addAttribute("totalPeliculas", peliculas.size());
        model.addAttribute("hayAnterior", id > 0);
        model.addAttribute("haySiguiente", id < peliculas.size() - 1);
        model.addAttribute("idAnterior", id - 1);
        model.addAttribute("idSiguiente", id + 1);

        return "pelicula";
    }
}

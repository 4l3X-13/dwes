package com.example.plantillasbasicas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlantillasBasicasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(containsString("Listado de películas")))
                .andExpect(content().string(containsString("/peliculas")))
                .andExpect(content().string(containsString("/pelicula/0")))
                .andExpect(content().string(containsString("/inicializar")));
    }

    @Test
    void testInicializarEndpoint() throws Exception {
        mockMvc.perform(get("/inicializar"))
                .andExpect(status().isOk())
                .andExpect(view().name("inicializar"))
                .andExpect(model().attributeExists("peliculas"))
                .andExpect(model().attributeExists("totalPeliculas"))
                .andExpect(content().string(containsString("The Matrix")));
    }

    @Test
    void testListarPeliculasEndpoint() throws Exception {
        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(view().name("peliculas"))
                .andExpect(model().attributeExists("peliculas"))
                .andExpect(content().string(containsString("The Matrix")))
                .andExpect(content().string(containsString("Pulp Fiction")))
                .andExpect(content().string(containsString("Ver detalle")))
                .andExpect(content().string(containsString("https://www.imdb.com/title/tt0133093/")));
    }

    @Test
    void testVerPeliculaClasica() throws Exception {
        // The Matrix (1999) es anterior a 2000 -> es clásico
        mockMvc.perform(get("/pelicula/0"))
                .andExpect(status().isOk())
                .andExpect(view().name("pelicula"))
                .andExpect(model().attributeExists("pelicula"))
                .andExpect(content().string(containsString("The Matrix")))
                .andExpect(content().string(containsString("Ciencia Ficción")))
                .andExpect(content().string(containsString("8.7")))
                .andExpect(content().string(containsString("Esta película es un clásico")))
                .andExpect(content().string(containsString("https://www.imdb.com/title/tt0133093/")));
    }

    @Test
    void testVerPeliculaModerna() throws Exception {
        // Interstellar (2014) es posterior o igual a 2000 -> no es clásico (unless)
        mockMvc.perform(get("/pelicula/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("pelicula"))
                .andExpect(model().attributeExists("pelicula"))
                .andExpect(content().string(containsString("Interstellar")))
                .andExpect(content().string(containsString("Película moderna")));
    }
}

package daw2.dwes.morty1.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aventuras")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "planeta_id", nullable = false)
    private Long planetaId;

    @Column(name = "personaje_id", nullable = false)
    private Long personajeId;

    public Episode() {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Long getPlanetaId() { return planetaId; }
    public void setPlanetaId(Long planetaId) { this.planetaId = planetaId; }

    public Long getPersonajeId() { return personajeId; }
    public void setPersonajeId(Long personajeId) { this.personajeId = personajeId; }
}
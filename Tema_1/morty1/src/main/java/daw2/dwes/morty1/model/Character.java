package daw2.dwes.morty1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "personajes")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 50)
    private String especie;

    @Column(length = 20)
    private String dimension;

    @Column(name = "habilidad_especial", length = 100)
    private String habilidadEspecial;

    @Column(length = 10)
    private String genero;

    @Column(name = "nivel_inteligencia")
    private Integer nivelInteligencia;

    @Column(length = 50)
    private String afiliacion;

    public Character() {
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }

    public String getHabilidadEspecial() { return habilidadEspecial; }
    public void setHabilidadEspecial(String habilidadEspecial) { this.habilidadEspecial = habilidadEspecial; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getNivelInteligencia() { return nivelInteligencia; }
    public void setNivelInteligencia(Integer nivelInteligencia) { this.nivelInteligencia = nivelInteligencia; }

    public String getAfiliacion() { return afiliacion; }
    public void setAfiliacion(String afiliacion) { this.afiliacion = afiliacion; }
}
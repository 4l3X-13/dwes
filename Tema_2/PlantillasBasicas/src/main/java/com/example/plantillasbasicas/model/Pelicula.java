package com.example.plantillasbasicas.model;

import java.time.LocalDate;

/**
 * Modelo de Película con los atributos requeridos:
 * título, género, puntuación, url (IMDB), urlPortada y fecha de estreno.
 */
public class Pelicula {

    private String titulo;
    private String genero;
    private double puntuacion;
    private String url;
    private String urlPortada;
    private LocalDate fechaEstreno;

    public Pelicula() {
    }

    public Pelicula(String titulo, String genero, double puntuacion, String url, String urlPortada, LocalDate fechaEstreno) {
        this.titulo = titulo;
        this.genero = genero;
        this.puntuacion = puntuacion;
        this.url = url;
        this.urlPortada = urlPortada;
        this.fechaEstreno = fechaEstreno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    /**
     * Devuelve true si la fecha de estreno es anterior al año 2000.
     * Facilita el uso en expresiones booleanas de Thymeleaf con th:if y th:unless.
     */
    public boolean isClasica() {
        return this.fechaEstreno != null && this.fechaEstreno.getYear() < 2000;
    }

    public boolean getEsClasica() {
        return isClasica();
    }

    public int getAnio() {
        return this.fechaEstreno != null ? this.fechaEstreno.getYear() : 0;
    }
}

package org.colecciones.entidades;

public record Book(String isbn, String title, String author, int pages) {
    public int getPaginas() {
        return pages;
    }
}
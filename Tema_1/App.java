package org.colecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.colecciones.entidades.Book;
import org.colecciones.entidades.BookCollection;

public class App {
    public static void main(String[] args) {
        ArrayList<Book> libros = new ArrayList<>();

        libros.add(new Book("9788422616337", "El Señor de los Anillos", "J.R.R. Tolkien", 800));
        libros.add(new Book("9788445077528", "El Hobbit", "J.R.R. Tolkien", 350));
        libros.add(new Book("9788466316781", "Cabo Trafalgar", "Arturo Pérez Reverte", 320));
        libros.add(new Book("9788493975074", "El corazón de la piedra", "José María García López", 560));
        libros.add(new Book("9788493291488", "Salmos de vísperas", "Esteban Hernández Castelló", 95));
        libros.add(new Book("9788420685625", "La música en las catedrales españolas del Siglo de Oro", "Robert Stevenson", 600));
        libros.add(new Book("9788423913077", "Luces de bohemia", "Ramón del Valle-Inclán", 296));
        libros.add(new Book("9788448031121", "Contando atardeceres", "La vecina rubia", 528));
        libros.add(new Book("9781529342079", "The Master: The Brilliant Career of Roger Federer", "Christopher Clarey", 456));
        libros.add(new Book("9788408264385", "La teoría de los archipiélagos", "Alice Kellen", 300));
        libros.add(new Book("9788423362479", "Esperando al diluvio", "Dolores Redondo", 576));
        libros.add(new Book("9788466367349", "El italiano", "Arturo Pérez Reverte", 400));
        libros.add(new Book("9788466359290", "Línea de fuego", "Arturo Pérez Reverte", 688));

        BookCollection coleccion = new BookCollection();

        long totalMas500 = coleccion.contarLibrosMas500Paginas(libros);
        long totalMenos300 = coleccion.contarLibrosMenos300Paginas(libros);
        List<String> titulosMas500 = coleccion.obtenerTitulosMas500Paginas(libros);
        List<String> top3MasPaginas = coleccion.obtenerTop3LibrosMasPaginas(libros);
        int sumaPaginas = coleccion.obtenerSumaTotalPaginas(libros);
        List<Book> librosSobrePromedio = coleccion.obtenerLibrosSobrePromedio(libros);
        List<String> autoresUnicos = coleccion.obtenerAutoresUnicos(libros);
        List<String> autoresMasDeUnLibro = coleccion.obtenerAutoresConMasDeUnLibro(libros);
        Optional<Book> libroMasLargo = coleccion.obtenerLibroConMasPaginas(libros);
        List<String> todosLosTitulos = coleccion.obtenerTodosLosTitulos(libros);

        System.out.println("Libros con más de 500 páginas: " + totalMas500);
        System.out.println("Libros con menos de 300 páginas: " + totalMenos300);

        System.out.println("\nTítulos de libros con más de 500 páginas:");
        titulosMas500.forEach(titulo -> System.out.println("- " + titulo));

        System.out.println("\nLos 3 libros con mayor número de páginas:");
        top3MasPaginas.forEach(titulo -> System.out.println("- " + titulo));

        System.out.println("\nSuma total de páginas de todos los libros: " + sumaPaginas);

        System.out.println("\nLibros que superan el promedio de páginas:");
        librosSobrePromedio.forEach(libro -> System.out.println("- " + libro.title() + " (" + libro.getPaginas() + " págs.)"));

        System.out.println("\nAutores (sin repetir):");
        autoresUnicos.forEach(autor -> System.out.println("- " + autor));

        System.out.println("\nAutores con más de 1 libro:");
        autoresMasDeUnLibro.forEach(autor -> System.out.println("- " + autor));

        System.out.println("\nLibro con mayor número de páginas:");
        libroMasLargo.ifPresent(libro -> System.out.println("- " + libro.title() + " por " + libro.author() + " (" + libro.getPaginas() + " págs.)"));

        System.out.println("\nTodos los títulos de la colección:");
        todosLosTitulos.forEach(titulo -> System.out.println("- " + titulo));
    }
}
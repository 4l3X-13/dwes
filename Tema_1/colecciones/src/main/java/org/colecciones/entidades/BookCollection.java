package org.colecciones.entidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookCollection {


    public long contarLibrosMas500Paginas(ArrayList<Book> libros) {
        long contador = 0;

        for (Book libro : libros) {
            if (libro.getPaginas() > 500) {
                contador++;
            }
        }

        return contador;
    }

    public long contarLibrosMenos300Paginas(ArrayList<Book> libros) {
        long contador = 0;

        for (Book libro : libros) {
            if (libro.getPaginas() < 300) {
                contador++;
            }
        }

        return contador;
    }

    public List<String> obtenerTitulosMas500Paginas(ArrayList<Book> libros) {
        List<String> titulos = new ArrayList<>();

        for (Book libro : libros) {
            if (libro.getPaginas() > 500) {
                titulos.add(libro.title());
            }
        }

        return titulos;
    }

    public List<String> obtenerTop3LibrosMasPaginas(ArrayList<Book> libros) {
        List<Book> copia = new ArrayList<>(libros);
        List<String> titulos = new ArrayList<>();

        // Ordenar de mayor a menor
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).getPaginas() > copia.get(i).getPaginas()) {
                    Book temporal = copia.get(i);
                    copia.set(i, copia.get(j));
                    copia.set(j, temporal);
                }
            }
        }

        // Obtener los 3 primeros
        int cantidad = Math.min(3, copia.size());

        for (int i = 0; i < cantidad; i++) {
            titulos.add(copia.get(i).title());
        }

        return titulos;
    }

    public int obtenerSumaTotalPaginas(ArrayList<Book> libros) {
        int suma = 0;

        for (Book libro : libros) {
            suma += libro.getPaginas();
        }

        return suma;
    }

    public List<Book> obtenerLibrosSobrePromedio(ArrayList<Book> libros) {
        List<Book> resultado = new ArrayList<>();

        if (libros.isEmpty()) {
            return resultado;
        }

        int suma = 0;

        for (Book libro : libros) {
            suma += libro.getPaginas();
        }

        double promedio = (double) suma / libros.size();

        for (Book libro : libros) {
            if (libro.getPaginas() > promedio) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    public List<String> obtenerAutoresUnicos(ArrayList<Book> libros) {
        List<String> autores = new ArrayList<>();

        for (Book libro : libros) {
            String autor = libro.author();

            if (!autores.contains(autor)) {
                autores.add(autor);
            }
        }

        return autores;
    }

    public List<String> obtenerAutoresConMasDeUnLibro(ArrayList<Book> libros) {
        List<String> autores = new ArrayList<>();

        for (Book libro : libros) {
            String autor = libro.author();
            int contador = 0;

            for (Book libro2 : libros) {
                if (libro2.author().equals(autor)) {
                    contador++;
                }
            }

            if (contador > 1 && !autores.contains(autor)) {
                autores.add(autor);
            }
        }

        return autores;
    }

    public Optional<Book> obtenerLibroConMasPaginas(ArrayList<Book> libros) {
        if (libros.isEmpty()) {
            return Optional.empty();
        }

        Book libroMayor = libros.get(0);

        for (Book libro : libros) {
            if (libro.getPaginas() > libroMayor.getPaginas()) {
                libroMayor = libro;
            }
        }

        return Optional.of(libroMayor);
    }

    public List<String> obtenerTodosLosTitulos(ArrayList<Book> libros) {
        List<String> titulos = new ArrayList<>();

        for (Book libro : libros) {
            titulos.add(libro.title());
        }

        return titulos;
    }



    public long contarLibrosMas500Paginas2(ArrayList<Book> libros) {
        return libros.stream()
                .filter(libro -> libro.getPaginas() > 500)
                .count();
    }

    public long contarLibrosMenos300Paginas2(ArrayList<Book> libros) {
        return libros.stream()
                .filter(libro -> libro.getPaginas() < 300)
                .count();
    }

    public List<String> obtenerTitulosMas500Paginas2(ArrayList<Book> libros) {
        return libros.stream()
                .filter(libro -> libro.getPaginas() > 500)
                .map(Book::title)
                .toList();
    }

    public List<String> obtenerTop3LibrosMasPaginas2(ArrayList<Book> libros) {
        return libros.stream()
                .sorted(Comparator.comparingInt(Book::getPaginas).reversed())
                .limit(3)
                .map(Book::title)
                .toList();
    }

    public int obtenerSumaTotalPaginas2(ArrayList<Book> libros) {
        return libros.stream()
                .mapToInt(Book::getPaginas)
                .sum();
    }

    public List<Book> obtenerLibrosSobrePromedio2(ArrayList<Book> libros) {
        double promedio = libros.stream()
                .mapToInt(Book::getPaginas)
                .average()
                .orElse(0.0);

        return libros.stream()
                .filter(libro -> libro.getPaginas() > promedio)
                .toList();
    }

    public List<String> obtenerAutoresUnicos2(ArrayList<Book> libros) {
        return libros.stream()
                .map(Book::author)
                .distinct()
                .toList();
    }

    public List<String> obtenerAutoresConMasDeUnLibro2(ArrayList<Book> libros) {
        return libros.stream()
                .collect(Collectors.groupingBy(Book::author, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Optional<Book> obtenerLibroConMasPaginas2(ArrayList<Book> libros) {
        return libros.stream()
                .max(Comparator.comparingInt(Book::getPaginas));
    }

    public List<String> obtenerTodosLosTitulos2(ArrayList<Book> libros) {
        return libros.stream()
                .map(Book::title)
                .toList();
    }






}
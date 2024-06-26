package task;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class Task2 {
    /*

    b) Crea una lista de todos los títulos de los libros. //Map
    c) Verifica si la biblioteca contiene al menos un libro de un autor específico.
    d) Encuentra el libro más barato en la biblioteca.
    e) imprime en consola todos los títulos de libros únicos en la biblioteca
    f) Problema: Crea un Map en el que las claves son los géneros de los libros y los valores son la cantidad de libros en cada género. Esto requiere que uses Collectors.groupingBy y Collectors.counting.
    g) Crear una lista de todos los libros publicados antes del año 2000.
    h) Calcular el precio promedio de los libros de "Ciencia Ficción".
    i) Crear una lista de todos los libros de "Historia", ordenados por año de publicación, de más reciente a más antiguo.
    j) Crear un mapa que agrupe los libros por género.
     */

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Book1", "Author1", 1995, "Novel", 19.99),
                new Book("Book1", "Author2", 2005, "Science Fiction", 29.99),
                new Book("Book3", "Author3", 1997, "History", 39.99),
                new Book("Book4", "Author4", 2000, "Novel", 22.99),
                new Book("Book5", "Author5", 2018, "Science Fiction", 33.99),
                new Book("Book6", "Author6", 1999, "History", 21.99),
                new Book("Book7", "Author7", 2021, "Novel", 25.99),
                new Book("Book8", "Author8", 2010, "Science Fiction", 32.99),
                new Book("Book9", "Author9", 1980, "History", 29.99),
                new Book("Book10", "Author10", 1990, "Novel", 9.99),
                new Book("Book11", "Author11", 1998, "Science Fiction", 24.99),
                new Book("Book12", "Author12", 2002, "History", 34.99),
                new Book("Book13", "Author13", 2019, "Novel", 20.99),
                new Book("Book14", "Author14", 2012, "Science Fiction", 30.99),
                new Book("Book15", "Author15", 1996, "History", 23.99),
                new Book("Book16", "Author16", 2020, "Novel", 26.99),
                new Book("Book17", "Author17", 2008, "Science Fiction", 28.99),
                new Book("Book18", "Author18", 1993, "History", 27.99),
                new Book("Book19", "Author19", 2011, "Novel", 29.99),
                new Book("Book20", "Author20", 2003, "Science Fiction", 35.99)
        );
        //TODO: Calcular el total de numero total en la biblioteca
        //Contar los libros que tiene la lista
        Long countBooks = books.stream().count();

       // b) Crea una lista de todos los títulos de los libros. //Map

        List<String> titleList = books.stream()
                .map(Book::getTitle).collect(Collectors.toList());

        //c) Verifica si la biblioteca contiene al menos un libro de un autor específico.
        Predicate<Book> verificarAutor = book -> book.getAuthor().equals("Author1");
        boolean AuthorTieneLibro = books.stream().anyMatch(verificarAutor);
        System.out.println(AuthorTieneLibro);

        //d) Encuentra el libro más barato en la biblioteca.
        Comparator<Book> comparadorPrecio = Comparator.comparingDouble(Book::getPrice);
        Optional<Book> cheapestBook= books.stream().min(comparadorPrecio);

        //e) imprime en consola todos los títulos de libros únicos en la biblioteca
       List<String> uniqueTitles = books.stream().map(Book::getTitle).distinct().collect(Collectors.toList());
        System.out.println(uniqueTitles);

        //f) Problema: Crea un Map en el que las claves son los géneros de los libros
        // y los valores son la cantidad de libros en cada género.
        // Esto requiere que uses Collectors.groupingBy y Collectors.counting.
        Map<String, Long> bookProGenre = books.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));

        //g) Crear una lista de todos los libros publicados antes del año 2000.
        List<Book> publishedBefore2000 = books.stream().filter(x-> x.getYear()<2000).collect(Collectors.toList());

        //h) Calcular el precio promedio de los libros de "Ciencia Ficción"
        double averagePriceFiction = books.stream().mapToDouble(Book::getPrice).average().getAsDouble();

        //i) Crear una lista de todos los libros de "Historia", ordenados por año de publicación, de más reciente a más antiguo.
        List<Book> historyBooks = books.stream()
                .filter(x->x.getGenre()
                        .equals("History"))
                .sorted(Comparator.comparingInt(Book::getYear))
                .collect(Collectors.toList());

        //j) Crear un mapa que agrupe los libros por género.

        Map<String,List<Book>> bookByGenre = books.stream().collect(Collectors.groupingBy(Book::getGenre));
    }
}

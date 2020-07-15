package by.balashevich.bookapp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookStorage {
    private static final int MAX_CAPACITY = 50;
    private static BookStorage instance;

    private List<Book> books;

    private BookStorage() {
        books = new ArrayList<>();
        books.add(new Book("The Lord Of The Rings", new ArrayList<>(Arrays.asList("J.R.R.Tolkien")),
                1954, Language.ENGLISH));
        books.add(new Book("Airport", new ArrayList<>(Arrays.asList("A. Heiley")),
                1968, Language.ENGLISH));
        books.add(new Book("Three comrades", new ArrayList<>(Arrays.asList("E. M. Remarque")),
                1936, Language.GERMAN));
        books.add(new Book("Kindred", new ArrayList<>(Arrays.asList("A. Pekhov", "E. Bychkova", "N. Turchaninova")),
                2006, Language.RUSSIAN));
        books.add(new Book("Divine comedy", new ArrayList<>(Arrays.asList("Dante Alighieri")),
                1265, Language.ITALIAN));
        books.add(new Book("The Hobbit", new ArrayList<>(Arrays.asList("J.R.R.Tolkien")),
                1937, Language.ENGLISH));
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }

        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public boolean addBook(Book book) {
        boolean isAdded = false;

        if (!books.contains(book) && books.size() < MAX_CAPACITY) {
            isAdded = books.add(book);
        }

        return isAdded;
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

}

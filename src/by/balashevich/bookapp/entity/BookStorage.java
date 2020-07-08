package by.balashevich.bookapp.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStorage {
    private static BookStorage instance;

    private List<Book> books;

    private BookStorage() {
        books = new ArrayList<>();
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
        return books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public int getSize() {
        return books.size();
    }
}

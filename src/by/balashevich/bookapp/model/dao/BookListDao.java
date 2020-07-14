package by.balashevich.bookapp.model.dao;

import by.balashevich.bookapp.exception.DaoApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void add(Book book) throws DaoApplicationException;

    void remove(Book book) throws DaoApplicationException;

    Optional<Book> findById(long bookId);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByYearPublication(int yearPublication);

    List<Book> findByLanguage(Language language);

    List<Book> sortById();

    List<Book> sortByTitle();

    List<Book> sortByAuthor();

    List<Book> sortByYearPublication();

    List<Book> sortByLanguage();
}

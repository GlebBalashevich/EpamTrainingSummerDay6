package by.balashevich.bookapp.dao;

import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.entity.Language;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void addBook(Book book) throws ApplicationInvalidDataException;

    void removeBook(Book book) throws ApplicationInvalidDataException;

    Optional<Book> findById(long bookId);

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

package by.balashevich.bookapp.dao;

import by.balashevich.bookapp.exception.DaoApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.exception.ServiceApplicationException;

import java.util.List;
import java.util.Optional;

public interface BookListDao {
    void addBook(Book book) throws ServiceApplicationException, DaoApplicationException;

    void removeBook(Book book) throws ServiceApplicationException, DaoApplicationException;

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

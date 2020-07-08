package by.balashevich.bookapp.service;

import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.entity.Language;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;

import java.util.List;
import java.util.Optional;

public interface BookFindService {
    Optional<Book> findById(long bookId);

    List<Book> findByTitle(String title) throws ApplicationInvalidDataException;

    List<Book> findByAuthor(String author) throws ApplicationInvalidDataException;

    List<Book> findByYearPublication(int yearPublication) throws ApplicationInvalidDataException;

    List<Book> findByLanguage(Language language) throws ApplicationInvalidDataException;
}

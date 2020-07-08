package by.balashevich.bookapp.service;

import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;

import java.util.List;

public interface BookSortService {

    List<Book> sortById() throws ApplicationInvalidDataException;

    List<Book> sortByTitle() throws ApplicationInvalidDataException;

    List<Book> sortByAuthor() throws ApplicationInvalidDataException;

    List<Book> sortByYearPublication() throws ApplicationInvalidDataException;

    List<Book> sortByLanguage() throws ApplicationInvalidDataException;
}

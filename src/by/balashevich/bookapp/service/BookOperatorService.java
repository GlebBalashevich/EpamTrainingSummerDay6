package by.balashevich.bookapp.service;

import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;

public interface BookOperatorService {

    void addBook(Book book) throws ApplicationInvalidDataException;

    void removeBook(Book book) throws ApplicationInvalidDataException;
}

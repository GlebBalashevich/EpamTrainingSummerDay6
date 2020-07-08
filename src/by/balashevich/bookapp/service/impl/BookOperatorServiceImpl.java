package by.balashevich.bookapp.service.impl;

import by.balashevich.bookapp.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;
import by.balashevich.bookapp.service.BookOperatorService;

public class BookOperatorServiceImpl implements BookOperatorService {

    @Override
    public void addBook(Book book) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        bookListDao.addBook(book);
    }

    @Override
    public void removeBook(Book book) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        bookListDao.removeBook(book);
    }
}

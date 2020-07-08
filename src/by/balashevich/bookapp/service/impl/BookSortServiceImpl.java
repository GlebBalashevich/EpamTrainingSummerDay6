package by.balashevich.bookapp.service.impl;

import by.balashevich.bookapp.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;
import by.balashevich.bookapp.service.BookSortService;

import java.util.List;

public class BookSortServiceImpl implements BookSortService {

    @Override
    public List<Book> sortById() throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortById();

        if (sortedList.isEmpty()) {
            throw new ApplicationInvalidDataException("Error in sorting by Id method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByTitle() throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByTitle();

        if (sortedList.isEmpty()) {
            throw new ApplicationInvalidDataException("Error in sorting by Title method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByAuthor() throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByAuthor();

        if (sortedList.isEmpty()) {
            throw new ApplicationInvalidDataException("Error in sorting by Author method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByYearPublication() throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByYearPublication();

        if (sortedList.isEmpty()) {
            throw new ApplicationInvalidDataException("Error in sorting by Year publication method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByLanguage() throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByLanguage();

        if (sortedList.isEmpty()) {
            throw new ApplicationInvalidDataException("Error in sorting by Language method");
        }

        return sortedList;
    }
}

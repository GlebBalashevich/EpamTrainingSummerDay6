package by.balashevich.bookapp.model.service.impl;

import by.balashevich.bookapp.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.exception.DaoApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.model.service.BookService;
import by.balashevich.bookapp.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Override
    public void addBook(Book book) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        try {
            bookListDao.addBook(book);
        } catch (DaoApplicationException e) {
            throw new ServiceApplicationException("Error while adding book to storage", e);
        }
    }

    @Override
    public void removeBook(Book book) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        try {
            bookListDao.removeBook(book);
        } catch (DaoApplicationException e) {
            throw new ServiceApplicationException("Error while removing book from storage", e);
        }
    }

    @Override
    public Optional<Book> findById(long bookId) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();

        return bookListDao.findById(bookId);
    }

    @Override
    public List<Book> findByTitle(String title) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateTitle(title)) {
            throw new ServiceApplicationException("Invalid search by Title request");
        }

        return bookListDao.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateSingleAuthor(author)) {
            throw new ServiceApplicationException("Invalid search by Author request");
        }

        return bookListDao.findByAuthor(author);
    }

    @Override
    public List<Book> findByYearPublication(int yearPublication) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateYearPublication(yearPublication)) {
            throw new ServiceApplicationException("Invalid search by Year publication request");
        }

        return bookListDao.findByYearPublication(yearPublication);
    }

    @Override
    public List<Book> findByLanguage(Language language) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();

        if (language == null) {
            throw new ServiceApplicationException("Invalid search by Language request");
        }

        return bookListDao.findByLanguage(language);
    }

    @Override
    public List<Book> sortById() throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortById();

        if (sortedList.isEmpty()) {
            throw new ServiceApplicationException("Error in sorting by Id method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByTitle() throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByTitle();

        if (sortedList.isEmpty()) {
            throw new ServiceApplicationException("Error in sorting by Title method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByAuthor() throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByAuthor();

        if (sortedList.isEmpty()) {
            throw new ServiceApplicationException("Error in sorting by Author method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByYearPublication() throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByYearPublication();

        if (sortedList.isEmpty()) {
            throw new ServiceApplicationException("Error in sorting by Year publication method");
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByLanguage() throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByLanguage();

        if (sortedList.isEmpty()) {
            throw new ServiceApplicationException("Error in sorting by Language method");
        }

        return sortedList;
    }
}

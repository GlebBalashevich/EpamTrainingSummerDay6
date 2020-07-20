package by.balashevich.bookapp.model.service.impl;

import by.balashevich.bookapp.model.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.exception.DaoApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.model.service.BookService;
import by.balashevich.bookapp.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> addBook(Book book) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> bookList;

        try {
            bookList = bookListDao.add(book);
        } catch (DaoApplicationException e) {
            throw new ServiceApplicationException("Error while adding book to storage", e);
        }

        return bookList;
    }

    @Override
    public List<Book> removeBook(Book book) throws ServiceApplicationException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> bookList;

        try {
            bookList = bookListDao.remove(book);
        } catch (DaoApplicationException e) {
            throw new ServiceApplicationException("Error while removing book from storage", e);
        }

        return bookList;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        Optional<Book> targetBook = bookListDao.findById(bookId);

        return targetBook;
    }

    @Override
    public List<Book> findByTitle(String title) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();
        List<Book> targetBooks = new ArrayList<>();

        if (bookValidator.validateTitle(title)) {
            targetBooks = bookListDao.findByTitle(title);
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();
        List<Book> targetBooks = new ArrayList<>();

        if (bookValidator.validateSingleAuthor(author)) {
            targetBooks = bookListDao.findByAuthor(author);
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByYearPublication(int yearPublication) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();
        List<Book> targetBooks = new ArrayList<>();

        if (bookValidator.validateYearPublication(yearPublication)) {
            targetBooks = bookListDao.findByYearPublication(yearPublication);
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByLanguage(Language language) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> targetBooks = new ArrayList<>();

        if (language != null) {
            targetBooks = bookListDao.findByLanguage(language);
        }

        return targetBooks;
    }

    @Override
    public List<Book> sortById() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortById();

        return sortedList;
    }

    @Override
    public List<Book> sortByTitle() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByTitle();

        return sortedList;
    }

    @Override
    public List<Book> sortByAuthor() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByAuthor();

        return sortedList;
    }

    @Override
    public List<Book> sortByYearPublication() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByYearPublication();

        return sortedList;
    }

    @Override
    public List<Book> sortByLanguage() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = bookListDao.sortByLanguage();

        return sortedList;
    }
}

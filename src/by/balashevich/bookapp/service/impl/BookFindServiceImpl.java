package by.balashevich.bookapp.service.impl;

import by.balashevich.bookapp.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.entity.Language;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;
import by.balashevich.bookapp.service.BookFindService;
import by.balashevich.bookapp.validator.BookValidator;

import java.util.List;
import java.util.Optional;

public class BookFindServiceImpl implements BookFindService {
    @Override
    public Optional<Book> findById(long bookId) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();

        return bookListDao.findById(bookId);
    }

    @Override
    public List<Book> findByTitle(String title) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateTitle(title)) {
            throw new ApplicationInvalidDataException("Invalid search by Title request");
        }

        return bookListDao.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateSingleAuthor(author)) {
            throw new ApplicationInvalidDataException("Invalid search by Author request");
        }

        return bookListDao.findByAuthor(author);
    }

    @Override
    public List<Book> findByYearPublication(int yearPublication) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator bookValidator = new BookValidator();

        if (!bookValidator.validateYearPublication(yearPublication)) {
            throw new ApplicationInvalidDataException("Invalid search by Year publication request");
        }

        return bookListDao.findByYearPublication(yearPublication);
    }

    @Override
    public List<Book> findByLanguage(Language language) throws ApplicationInvalidDataException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();

        if (language == null) {
            throw new ApplicationInvalidDataException("Invalid search by Language request");
        }

        return bookListDao.findByLanguage(language);
    }
}

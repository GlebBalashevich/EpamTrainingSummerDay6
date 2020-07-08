package by.balashevich.bookapp.dao.impl;

import by.balashevich.bookapp.dao.BookListDao;
import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.entity.Language;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;
import by.balashevich.bookapp.entity.BookStorage;

import java.util.*;

public class BookListDaoImpl implements BookListDao {

    @Override
    public void addBook(Book book) throws ApplicationInvalidDataException {
        List<Book> books = BookStorage.getInstance().getBooks();
        boolean addingResult = false;

        if (!books.contains(book)) {
            addingResult = BookStorage.getInstance().addBook(book);
        }

        if(!addingResult){
            throw new ApplicationInvalidDataException("Error while adding book to storage");
        }

    }

    @Override
    public void removeBook(Book book) throws ApplicationInvalidDataException {
        if(!BookStorage.getInstance().removeBook(book)){
            throw new ApplicationInvalidDataException("Error while removing book from storage");
        }
    }

    @Override
    public Optional<Book> findById(long bookId) {
        List<Book> books = BookStorage.getInstance().getBooks();
        Optional<Book> targetBook = Optional.empty();

        for (Book book : books) {
            if (book.getBookId() == bookId) {
                targetBook = Optional.of(book);
            }
        }

        return targetBook;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> targetBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                targetBooks.add(book);
            }
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> targetBooks = new ArrayList<>();

        for (Book book : books) {
            List<String> authors = book.getAuthors();

            for (String targetAuthor : authors) {
                if (targetAuthor.equals(author)) {
                    targetBooks.add(book);
                }
            }
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByYearPublication(int yearPublication) {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> targetBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getYearPublication() == yearPublication) {
                targetBooks.add(book);
            }
        }

        return targetBooks;
    }

    @Override
    public List<Book> findByLanguage(Language language) {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> targetBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getLanguage() == language) {
                targetBooks.add(book);
            }
        }

        return targetBooks;
    }

    @Override
    public List<Book> sortById() {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> sortedBooks = new ArrayList<>(books);

        sortedBooks.sort(Comparator.comparingLong(Book::getBookId));

        return sortedBooks;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> sortedBooks = new ArrayList<>(books);

        sortedBooks.sort(Comparator.comparing(Book::getTitle));

        return sortedBooks;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> sortedBooks = new ArrayList<>(books);

        sortedBooks.sort(Comparator.comparingInt((Book book) -> book.getAuthors().size()).
                thenComparing(book -> book.getAuthors().get(0)));

        return sortedBooks;
    }

    @Override
    public List<Book> sortByYearPublication() {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> sortedBooks = new ArrayList<>(books);

        sortedBooks.sort(Comparator.comparingInt(Book::getYearPublication));

        return sortedBooks;
    }

    @Override
    public List<Book> sortByLanguage() {
        List<Book> books = BookStorage.getInstance().getBooks();
        List<Book> sortedBooks = new ArrayList<>(books);

        sortedBooks.sort(Comparator.comparing((Book book) -> book.getLanguage().getName()));

        return sortedBooks;
    }

}

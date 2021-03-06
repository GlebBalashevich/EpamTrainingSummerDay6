package test.balashevich.bookapp.model.service.impl;

import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookServiceImplTest {
    BookServiceImpl bookService;

    @BeforeTest
    public void setUp() {
        bookService = new BookServiceImpl();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void addBookTestPositive() throws ServiceApplicationException {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        Book addingBook = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")), 1928, Language.RUSSIAN);
        expected.add(addingBook);
        List<Book> actual = bookService.addBook(addingBook);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceApplicationException.class)
    public void addBookTestException() throws ServiceApplicationException {
        Book addingBook = FakeBookStorage.getBaseBookList().get(0);
        bookService.addBook(addingBook);
    }

    @Test
    public void removeBookTestPositive() throws ServiceApplicationException {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        Book removingBook = expected.get(0);
        expected.remove(removingBook);
        List<Book> actual = bookService.removeBook(removingBook);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceApplicationException.class)
    public void removeBookTestException() throws ServiceApplicationException {
        Book removingBook = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")), 1928, Language.RUSSIAN);
        bookService.removeBook(removingBook);
    }

    @Test
    public void findByIdTestPositive() {
        long id = 3;
        Optional<Book> expected = Optional.empty();
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        for (Book book : baseBookList) {
            if (book.getBookId() == id) {
                expected = Optional.of(book);
            }
        }

        Optional<Book> actual = bookService.findById(id);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestNegative() {
        long id = 101;
        Optional<Book> expected = Optional.empty();

        Optional<Book> actual = bookService.findById(id);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTitleTestPositive() {
        String title = "12 Cheers";
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expected = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getTitle().equals(title)) {
                expected.add(book);
            }
        }

        List<Book> actual = bookService.findByTitle(title);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTitleTestNegative() {
        String title = "";
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookService.findByTitle(title);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTestPositive() {
        String author = "B.Strugatsky";
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expected = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getAuthors().contains(author)) {
                expected.add(book);
            }
        }

        List<Book> actual = bookService.findByAuthor(author);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTestNegative() {
        String author = "";
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookService.findByAuthor(author);
        assertEquals(actual, expected);
    }

    @Test
    public void findByYearPublicationTestPositive() {
        int yearPublication = 1925;
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expected = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getYearPublication() == yearPublication) {
                expected.add(book);
            }
        }

        List<Book> actual = bookService.findByYearPublication(yearPublication);
        assertEquals(actual, expected);
    }

    @Test
    public void findByYearPublicationTestNegative() {
        int yearPublication = 3201;
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookService.findByYearPublication(yearPublication);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLanguageTestPositive() {
        Language language = Language.RUSSIAN;
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expected = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getLanguage().equals(language)) {
                expected.add(book);
            }
        }

        List<Book> actual = bookService.findByLanguage(language);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLanguageTestNegative() {
        Language language = null;
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookService.findByLanguage(language);
        assertEquals(actual, expected);
    }

    @Test
    public void sortByIdTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByIdBookList();
        List<Book> actual = bookService.sortById();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByIdTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookService.sortById();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByTitleTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByTitleBookList();
        List<Book> actual = bookService.sortByTitle();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByTitleTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookService.sortByTitle();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByAuthorTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByAuthorBookList();
        List<Book> actual = bookService.sortByAuthor();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByAuthorTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookService.sortByAuthor();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByYearPublicationTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByYearPublicationBookList();
        List<Book> actual = bookService.sortByYearPublication();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByYearPublicationTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookService.sortByYearPublication();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByLanguageTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByLanguageBookList();
        List<Book> actual = bookService.sortByLanguage();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByLanguageTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookService.sortByLanguage();

        assertNotEquals(actual, expected);
    }
}
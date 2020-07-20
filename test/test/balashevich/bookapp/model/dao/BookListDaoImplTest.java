package test.balashevich.bookapp.model.dao;

import by.balashevich.bookapp.exception.DaoApplicationException;
import by.balashevich.bookapp.model.dao.impl.BookListDaoImpl;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    BookListDaoImpl bookListDao;

    @BeforeTest
    public void setUp() {
        bookListDao = new BookListDaoImpl();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void addTestPositive() throws DaoApplicationException {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        Book addingBook = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")), 1928, Language.RUSSIAN);
        expected.add(addingBook);
        List<Book> actual = bookListDao.add(addingBook);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoApplicationException.class)
    public void addTestException() throws DaoApplicationException {
        Book addingBook = FakeBookStorage.getBaseBookList().get(0);
        bookListDao.add(addingBook);
    }

    @Test
    public void removeTestPositive() throws DaoApplicationException {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        Book removingBook = expected.get(0);
        expected.remove(removingBook);
        List<Book> actual = bookListDao.remove(removingBook);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoApplicationException.class)
    public void removeTestException() throws DaoApplicationException {
        Book removingBook = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")), 1928, Language.RUSSIAN);
        bookListDao.remove(removingBook);
    }

    @Test
    public void findAllTestPositive() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.findAll();

        assertEquals(actual, expected);
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

        Optional<Book> actual = bookListDao.findById(id);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestNegative() {
        long id = 101;
        Optional<Book> expected = Optional.empty();

        Optional<Book> actual = bookListDao.findById(id);
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

        List<Book> actual = bookListDao.findByTitle(title);
        assertEquals(actual, expected);
    }

    @Test
    public void findByTitleTestNegative() {
        String title = "";
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookListDao.findByTitle(title);
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

        List<Book> actual = bookListDao.findByAuthor(author);
        assertEquals(actual, expected);
    }

    @Test
    public void findByAuthorTestNegative() {
        String author = "";
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookListDao.findByAuthor(author);
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

        List<Book> actual = bookListDao.findByYearPublication(yearPublication);
        assertEquals(actual, expected);
    }

    @Test
    public void findByYearPublicationTestNegative() {
        int yearPublication = 3201;
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookListDao.findByYearPublication(yearPublication);
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

        List<Book> actual = bookListDao.findByLanguage(language);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLanguageTestNegative() {
        Language language = null;
        List<Book> expected = new ArrayList<>();

        List<Book> actual = bookListDao.findByLanguage(language);
        assertEquals(actual, expected);
    }

    @Test
    public void sortByIdTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByIdBookList();
        List<Book> actual = bookListDao.sortById();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByIdTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.sortById();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByTitleTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByTitleBookList();
        List<Book> actual = bookListDao.sortByTitle();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByTitleTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.sortByTitle();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByAuthorTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByAuthorBookList();
        List<Book> actual = bookListDao.sortByAuthor();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByAuthorTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.sortByAuthor();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByYearPublicationTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByYearPublicationBookList();
        List<Book> actual = bookListDao.sortByYearPublication();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByYearPublicationTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.sortByYearPublication();

        assertNotEquals(actual, expected);
    }

    @Test
    public void sortByLanguageTestPositive() {
        List<Book> expected = FakeBookStorage.getSortedByLanguageBookList();
        List<Book> actual = bookListDao.sortByLanguage();

        assertEquals(actual, expected);
    }

    @Test
    public void sortByLanguageTestNegative() {
        List<Book> expected = FakeBookStorage.getBaseBookList();
        List<Book> actual = bookListDao.sortByLanguage();

        assertNotEquals(actual, expected);
    }
}
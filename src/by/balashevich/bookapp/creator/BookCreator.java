package by.balashevich.bookapp.creator;

import by.balashevich.bookapp.entity.Book;
import by.balashevich.bookapp.entity.Language;
import by.balashevich.bookapp.exception.ApplicationInvalidDataException;
import by.balashevich.bookapp.validator.BookValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookCreator {
    private static final String BOOK_PREFIX = "Book{";
    private static final String BOOK_SUFFIX = "}";
    private static final String BLANK = "";
    private static final String LINE_DELIMITER = ";";
    private static final String LIST_DELIMITER = ",";
    private static final String ELEMENT_DELIMITER = ":";
    private static final String LIST_PREFIX = "[";
    private static final String LIST_SUFFIX = "]";

    public Book createBook(String bookData) throws ApplicationInvalidDataException {
        BookValidator bookValidator = new BookValidator();
        bookData = bookData.replace(BOOK_PREFIX, BLANK).replace(BOOK_SUFFIX, BLANK);
        String[] bookElements = bookData.split(LINE_DELIMITER);
        String title = null;
        List<String> authors = null;
        int yearPublication = 0;
        Language language = null;

        for (String bookElement : bookElements) {
            String[] elementParts = bookElement.split(ELEMENT_DELIMITER);
            String fieldName = elementParts[0].trim();
            String fieldValue = elementParts[1].trim();

            if (fieldName.contains("title")) {
                title = fieldValue;
            }
            if (fieldName.contains("authors")) {
                authors = createList(fieldValue);
            }
            if (fieldName.contains("yearPublication")) {
                yearPublication = Integer.parseInt(fieldValue);
            }
            if (fieldName.contains("language")) {
                language = Language.valueOf(fieldValue);
            }
        }

        if (!bookValidator.validateBookElements(title, authors, yearPublication, language)) {
            throw new ApplicationInvalidDataException("Invalid data for book creating");
        }

        return new Book(title, authors, yearPublication, language);
    }

    public List<String> createList(String listData) {
        listData = listData.replace(LIST_PREFIX, BLANK).replace(LIST_SUFFIX, BLANK);
        String[] elementsList = listData.split(LIST_DELIMITER);
        List<String> resultList = new ArrayList<>();
        Collections.addAll(resultList, elementsList);

        return resultList;
    }

    public List<Book> createBooksList(List<String> booksData) throws ApplicationInvalidDataException {
        List<Book> booksList = new ArrayList<>();

        for (String bookData : booksData) {
            booksList.add(createBook(bookData));
        }

        return booksList;
    }
}

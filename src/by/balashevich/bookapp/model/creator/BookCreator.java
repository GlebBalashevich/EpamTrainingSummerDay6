package by.balashevich.bookapp.model.creator;

import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.validator.BookValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookCreator {
    private static final String LINE_DELIMITER = ";";
    private static final String LIST_DELIMITER = ",";
    private static final String ELEMENT_DELIMITER = ":";

    public Book createBook(String bookData) throws ServiceApplicationException {
        BookValidator bookValidator = new BookValidator();
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
            throw new ServiceApplicationException("Invalid data for book creating");
        }

        return new Book(title, authors, yearPublication, language);
    }

    public List<String> createList(String listData) {
        String[] elementsList = listData.split(LIST_DELIMITER);
        List<String> resultList = new ArrayList<>();
        Collections.addAll(resultList, elementsList);

        return resultList;
    }

    public List<Book> createBooksList(List<String> booksData) throws ServiceApplicationException {
        List<Book> booksList = new ArrayList<>();

        for (String bookData : booksData) {
            booksList.add(createBook(bookData));
        }

        return booksList;
    }
}

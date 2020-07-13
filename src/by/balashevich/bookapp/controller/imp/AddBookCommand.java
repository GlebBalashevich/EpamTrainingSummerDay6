package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.creator.BookCreator;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements ActionCommand {
    private static final String BOOK_PARAMS = "bookParams";

    @Override
    public Map<String, Object> execute(Map<String, Object> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        BookCreator bookCreator = new BookCreator();
        Book addingBook;
        Map<String, Object> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_PARAMS)) {
            try {
                addingBook = bookCreator.createBook((String) actionParameters.get(BOOK_PARAMS));
                bookService.addBook(addingBook);
                executeResult.put("refreshPage", "Book successfully added");

            } catch (ServiceApplicationException e) {
                throw new CommandApplicationException("Error with data", e);
            }
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

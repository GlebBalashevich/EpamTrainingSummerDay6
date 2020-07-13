package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.creator.BookCreator;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements ActionCommand {
    private static final String BOOK_PARAMS = "bookParams";

    @Override
    public Map<String, Object> execute(Map<String, Object> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        BookCreator bookCreator = new BookCreator();
        Book removingBook;
        Map<String, Object> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_PARAMS)) {
            try {
                removingBook = bookCreator.createBook((String) actionParameters.get(BOOK_PARAMS));
                bookService.removeBook(removingBook);
                executeResult.put("refreshPage", "Book successfully removed");

            } catch (ServiceApplicationException e) {
                throw new CommandApplicationException("Error with data", e);
            }
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

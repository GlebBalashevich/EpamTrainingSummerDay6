package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.creator.BookCreator;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AddBookCommand implements ActionCommand {
    private static final String BOOK_PARAMS = "bookParams";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        BookCreator bookCreator = new BookCreator();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_PARAMS)) {
            Optional<Book> transferredBook = bookCreator.createBook(actionParameters.get(BOOK_PARAMS));
            if (transferredBook.isPresent()) {
                try {
                    Book addingBook = transferredBook.get();
                    bookService.addBook(addingBook);
                    executeResult.put("refreshPage", "Book successfully added"); // FIXME: 14.07.2020 return map
                } catch (ServiceApplicationException e) {
                    throw new CommandApplicationException("Error with data", e);
                }
            } else {
                throw new CommandApplicationException("Error in command parameters");
            }
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

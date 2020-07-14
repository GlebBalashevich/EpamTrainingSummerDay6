package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindByIdCommand implements ActionCommand {
    private static final String BOOK_ID = "bookId";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_ID)) {
            long bookId = Long.parseLong(actionParameters.get(BOOK_ID));
            Optional<Book> findResult = bookService.findById(bookId); // FIXME: 14.07.2020 think about optional
            executeResult.put("findPage", findResult.toString()); // FIXME: 14.07.2020 return map
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

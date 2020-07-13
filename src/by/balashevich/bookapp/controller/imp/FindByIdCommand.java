package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindByIdCommand implements ActionCommand {
    private static final String BOOK_ID = "bookId";

    @Override
    public Map<String, Object> execute(Map<String, Object> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, Object> executeResult = new HashMap<>();
        Optional<Book> findResult;

        if (actionParameters.containsKey(BOOK_ID)) {
            findResult = bookService.findById((long) actionParameters.get(BOOK_ID));
            executeResult.put("findPage", findResult);
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

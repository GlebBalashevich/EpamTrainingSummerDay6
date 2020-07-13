package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByAuthorCommand implements ActionCommand {
    private static final String AUTHOR = "author";

    @Override
    public Map<String, Object> execute(Map<String, Object> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, Object> executeResult = new HashMap<>();
        List<Book> findResult;

        if (actionParameters.containsKey(AUTHOR)) {
            try {
                findResult = bookService.findByAuthor((String) actionParameters.get(AUTHOR));
                executeResult.put("findPage", findResult);
            } catch (ServiceApplicationException e) {
                throw new CommandApplicationException("Error with data", e);
            }
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}
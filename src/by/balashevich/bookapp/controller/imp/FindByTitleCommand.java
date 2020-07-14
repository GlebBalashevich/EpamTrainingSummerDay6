package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByTitleCommand implements ActionCommand {
    private static final String TITLE = "title";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(TITLE)) {
            String title = actionParameters.get(TITLE);
            List<Book> findResult = bookService.findByTitle(title);
            executeResult.put("findPage", findResult.toString()); // FIXME: 14.07.2020 return map
        } else {
            throw new CommandApplicationException("Error in command parameters");
        }

        return executeResult;
    }
}

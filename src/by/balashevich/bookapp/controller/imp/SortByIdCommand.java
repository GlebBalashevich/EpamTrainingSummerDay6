package by.balashevich.bookapp.controller.imp;

import by.balashevich.bookapp.controller.ActionCommand;
import by.balashevich.bookapp.exception.CommandApplicationException;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByIdCommand implements ActionCommand {

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) throws CommandApplicationException {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        try {
            List<Book> sortResult = bookService.sortById();
            executeResult.put("refreshPage", sortResult.toString()); // FIXME: 14.07.2020 return map
        } catch (ServiceApplicationException e) {
            throw new CommandApplicationException("Error with data", e);
        }

        return executeResult;
    }
}

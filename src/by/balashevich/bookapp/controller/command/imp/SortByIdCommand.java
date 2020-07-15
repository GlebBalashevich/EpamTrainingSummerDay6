package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.*;

public class SortByIdCommand implements ActionCommand {
    private static final String PAGE_MAIN = "page.path.main";
    private static final String PAGE_ERROR = "page.path.error";
    private static final String MESSAGE_APP_ERROR = "message.apperror";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters){
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        try {
            List<Book> sortResult = bookService.sortById();
            executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_MAIN));
            executeResult.put(BOOK_STORAGE.getName(), sortResult.toString());
        } catch (ServiceApplicationException e) {
            executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_ERROR));
            executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_APP_ERROR));
        }

        return executeResult;
    }
}
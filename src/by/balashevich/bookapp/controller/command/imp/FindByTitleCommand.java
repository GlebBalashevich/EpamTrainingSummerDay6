package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.*;

public class FindByTitleCommand implements ActionCommand {
    private static final String TITLE = "title";
    private static final String PAGE_SEARCH_RESULT = "page.path.searchresult";
    private static final String MESSAGE_FIND_EMPTY = "message.findempty";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(TITLE)) {
            String title = actionParameters.get(TITLE);
            List<Book> findResult = bookService.findByTitle(title);
            if (!findResult.isEmpty()) {
                executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_SEARCH_RESULT));
                executeResult.put(BOOK_STORAGE.getName(), findResult.toString());
            }
        }

        if (executeResult.isEmpty()) {
            executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_SEARCH_RESULT));
            executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_FIND_EMPTY));
        }

        return executeResult;
    }
}

package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.*;

public class FindByLanguageCommand implements ActionCommand {
    private static final String LANGUAGE = "language";
    private static final String PAGE_SEARCH_RESULT = "page.path.searchresult";
    private static final String MESSAGE_FIND_EMPTY = "message.findempty";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(LANGUAGE)) {
            Language language = Language.valueOf(actionParameters.get(LANGUAGE));
            List<Book> findResult = bookService.findByLanguage(language);
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

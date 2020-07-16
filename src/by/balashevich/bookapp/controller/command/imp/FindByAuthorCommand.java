package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByAuthorCommand implements ActionCommand {
    private static final String AUTHOR = "author";
    private static final String PAGE_SEARCH_RESULT = "page.path.searchresult";
    private static final String MESSAGE_FIND_EMPTY = "message.findempty";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters){
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(AUTHOR)) {
            String author = actionParameters.get(AUTHOR);
            List<Book> findResult = bookService.findByAuthor(author);
            if (!findResult.isEmpty()) {
                executeResult.put(ResponseParameterType.PAGE.getName(), ConfigurationManager.getProperty(PAGE_SEARCH_RESULT));
                executeResult.put(ResponseParameterType.BOOK_STORAGE.getName(), findResult.toString());
            }
        }

        if (executeResult.isEmpty()) {
            executeResult.put(ResponseParameterType.PAGE.getName(), ConfigurationManager.getProperty(PAGE_SEARCH_RESULT));
            executeResult.put(ResponseParameterType.MESSAGE.getName(), MessageManager.getMessage(MESSAGE_FIND_EMPTY));
        }

        return executeResult;
    }
}

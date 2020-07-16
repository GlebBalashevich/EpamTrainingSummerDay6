package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.*;

public class FindByIdCommand implements ActionCommand {
    private static final String BOOK_ID = "bookId";
    private static final String PAGE_ITEM_CARD = "page.path.itemcard";
    private static final String PAGE_MAIN = "page.path.main";
    private static final String MESSAGE_FIND_EMPTY = "message.findempty";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) {
        BookServiceImpl bookService = new BookServiceImpl();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_ID)) {
            long bookId = Long.parseLong(actionParameters.get(BOOK_ID));
            Optional<Book> findResult = bookService.findById(bookId);
            if (findResult.isPresent()) {
                executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_ITEM_CARD));
                executeResult.put(BOOK_STORAGE.getName(), findResult.get().toString());
            }
        }

        if (executeResult.isEmpty()) {
            executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_MAIN));
            executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_FIND_EMPTY));
        }

        return executeResult;
    }
}

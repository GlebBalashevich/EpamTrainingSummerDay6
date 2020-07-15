package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.exception.ServiceApplicationException;
import by.balashevich.bookapp.model.creator.BookCreator;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.service.impl.BookServiceImpl;
import by.balashevich.bookapp.util.ConfigurationManager;
import by.balashevich.bookapp.util.MessageManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.*;

public class AddBookCommand implements ActionCommand {
    private static final String BOOK_PARAMS = "bookParams";
    private static final String PAGE_MAIN = "page.path.main";
    private static final String PAGE_ERROR = "page.path.error";
    private static final String MESSAGE_ADD_SUCCESSFULLY = "message.addsuccessfully";
    private static final String MESSAGE_ADD_UNSUCCESSFULLY = "message.addunsuccessfully";
    private static final String MESSAGE_APP_ERROR = "message.apperror";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters){
        BookServiceImpl bookService = new BookServiceImpl();
        BookCreator bookCreator = new BookCreator();
        Map<String, String> executeResult = new HashMap<>();

        if (actionParameters.containsKey(BOOK_PARAMS)) {
            Optional<Book> transferredBook = bookCreator.createBook(actionParameters.get(BOOK_PARAMS));
            if (transferredBook.isPresent()) {
                try {
                    Book addingBook = transferredBook.get();
                    List<Book> bookList = bookService.addBook(addingBook);
                    executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_MAIN));
                    executeResult.put(BOOK_STORAGE.getName(), bookList.toString());
                    executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_ADD_SUCCESSFULLY));
                } catch (ServiceApplicationException e) {
                    executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_ERROR));
                    executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_APP_ERROR) + e.getMessage());
                }
            }
        }

        if (executeResult.isEmpty()){
            executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_MAIN));
            executeResult.put(MESSAGE.getName(), MessageManager.getMessage(MESSAGE_ADD_UNSUCCESSFULLY));
        }

        return executeResult;
    }
}

package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.FindByIdCommand;
import by.balashevich.bookapp.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class FindByIdCommandTest {
    FindByIdCommand findByIdCommand;

    @BeforeTest
    public void setUp() {
        findByIdCommand = new FindByIdCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestPositive() {
        String bookId = "3";
        Map<String, String> request = new HashMap<>();
        request.put("bookId", bookId);
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        Optional<Book> expectedBook = Optional.empty();

        for(Book book : baseBookList){
            if (book.getBookId() == Long.parseLong(bookId)){
                expectedBook = Optional.of(book);
            }
        }

        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.ITEMCARD.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedBook.get().toString());

        Map<String, String> actual = findByIdCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestEmptyResult() {
        String bookId = "3";
        Map<String, String> request = new HashMap<>();
        request.put("sort", bookId);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.FINDEMPTY.getText());

        Map<String, String> actual = findByIdCommand.execute(request);
        assertEquals(actual, expected);
    }
}
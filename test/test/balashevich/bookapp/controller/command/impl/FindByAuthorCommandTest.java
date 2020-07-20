package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.FindByAuthorCommand;
import by.balashevich.bookapp.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class FindByAuthorCommandTest {
    FindByAuthorCommand findByAuthorCommand;

    @BeforeTest
    public void setUp() {
        findByAuthorCommand = new FindByAuthorCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestPositive() {
        String author = "A.Strugatsky";
        Map<String, String> request = new HashMap<>();
        request.put("author", author);
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expectedList = new ArrayList<>();
        for(Book book : baseBookList){
            if (book.getAuthors().contains(author)){
                expectedList.add(book);
            }
        }
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = findByAuthorCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestEmptyResult() {
        String author = "A.Strugatsky";
        Map<String, String> request = new HashMap<>();
        request.put("sort", author);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.FINDEMPTY.getText());

        Map<String, String> actual = findByAuthorCommand.execute(request);
        assertEquals(actual, expected);
    }
}
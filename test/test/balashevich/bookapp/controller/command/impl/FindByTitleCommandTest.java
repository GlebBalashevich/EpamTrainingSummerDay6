package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.FindByTitleCommand;
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

public class FindByTitleCommandTest {
    FindByTitleCommand findByTitleCommand;

    @BeforeTest
    public void setUp() {
        findByTitleCommand = new FindByTitleCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestPositive() {
        String title = "Roadside Picnic";
        Map<String, String> request = new HashMap<>();
        request.put("title", title);
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expectedList = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getTitle().equals(title)) {
                expectedList.add(book);
            }
        }
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = findByTitleCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestEmptyResult() {
        String title = "Roadside Picnic";
        Map<String, String> request = new HashMap<>();
        request.put("sort", title);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.FINDEMPTY.getText());

        Map<String, String> actual = findByTitleCommand.execute(request);
        assertEquals(actual, expected);
    }
}
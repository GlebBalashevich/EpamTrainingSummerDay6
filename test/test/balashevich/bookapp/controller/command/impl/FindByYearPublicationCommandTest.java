package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.FindByYearPublicationCommand;
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

public class FindByYearPublicationCommandTest {
    FindByYearPublicationCommand findByYearPublicationCommand;

    @BeforeTest
    public void setUp() {
        findByYearPublicationCommand = new FindByYearPublicationCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestPositive() {
        String yearPublication = "1925";
        Map<String, String> request = new HashMap<>();
        request.put("yearPublication", yearPublication);
        List<Book> baseBookList = FakeBookStorage.getBaseBookList();
        List<Book> expectedList = new ArrayList<>();
        for (Book book : baseBookList) {
            if (book.getYearPublication() == Integer.parseInt(yearPublication)) {
                expectedList.add(book);
            }
        }
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = findByYearPublicationCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestEmptyResult() {
        String yearPublication = "1925";
        Map<String, String> request = new HashMap<>();
        request.put("sort", yearPublication);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.SEARCH_RESULT.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.FINDEMPTY.getText());

        Map<String, String> actual = findByYearPublicationCommand.execute(request);
        assertEquals(actual, expected);
    }
}
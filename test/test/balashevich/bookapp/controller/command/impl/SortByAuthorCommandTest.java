package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.SortByAuthorCommand;
import by.balashevich.bookapp.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class SortByAuthorCommandTest {
    SortByAuthorCommand sortByAuthorCommand;

    @BeforeTest
    public void setUp() {
        sortByAuthorCommand = new SortByAuthorCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestSort(){
        List<Book> expectedList = FakeBookStorage.getSortedByAuthorBookList();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = sortByAuthorCommand.execute(null);
        assertEquals(actual, expected);
    }
}
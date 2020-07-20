package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.SortByIdCommand;
import by.balashevich.bookapp.model.entity.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class SortByIdCommandTest {
    SortByIdCommand sortByIdCommand;

    @BeforeTest
    public void setUp() {
        sortByIdCommand = new SortByIdCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestSort(){
        List<Book> expectedList = FakeBookStorage.getSortedByIdBookList();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = sortByIdCommand.execute(null);
        assertEquals(actual, expected);
    }
}
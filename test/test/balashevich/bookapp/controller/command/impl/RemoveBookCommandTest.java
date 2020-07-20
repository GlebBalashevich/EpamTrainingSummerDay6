package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.RemoveBookCommand;
import by.balashevich.bookapp.model.creator.BookCreator;
import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.Language;
import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.balashevich.bookapp.model.entity.FakeBookStorage;

import java.util.*;

import static org.testng.Assert.*;

@PrepareForTest(RemoveBookCommand.class)
public class RemoveBookCommandTest extends PowerMockTestCase {
    RemoveBookCommand removeBookCommand;

    @BeforeTest
    public void setUp() {
        removeBookCommand = new RemoveBookCommand();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void executeTestRemoved() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("bookParams", "title : 1984; authors : J.Oruel; yearPublication : 1949; language : ENGLISH");
        List<Book> expectedList = FakeBookStorage.getBaseBookList();
        Book removingBook = expectedList.get(0);

        expectedList.remove(removingBook);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.REMOVESUCCESSFULLY.getText());

        BookCreator bookCreator = PowerMock.createMock(BookCreator.class);
        PowerMock.expectNew(BookCreator.class).andReturn(bookCreator).anyTimes();
        EasyMock.expect(bookCreator.createBook(EasyMock.anyString())).andReturn(Optional.of(removingBook)).anyTimes();
        PowerMock.replayAll();

        Map<String, String> actual = removeBookCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestNotRemovedByException() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("bookParams", "title : Master and Margarita; authors : M.Bulgakov; yearPublication : 1928; language : RUSSIAN");

        List<Book> expectedList = FakeBookStorage.getBaseBookList();
        Book removing = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")), 1928, Language.RUSSIAN);
        expectedList.remove(removing);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.ERROR.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.APPERROR.getText() + "Error while removing book from storage");

        BookCreator bookCreator = PowerMock.createMock(BookCreator.class);
        PowerMock.expectNew(BookCreator.class).andReturn(bookCreator).anyTimes();
        EasyMock.expect(bookCreator.createBook(EasyMock.anyString())).andReturn(Optional.of(removing)).anyTimes();
        PowerMock.replayAll();

        Map<String, String> actual = removeBookCommand.execute(request);
        assertEquals(actual, expected);
    }

    @Test
    public void executeTestNotAddedByRequest(){
        Map<String, String> request = new HashMap<>();
        request.put("fParams", "title : Faust; authors : I.V.Goethe; yearPublication : 1808; language : GERMAN");

        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.REMOVEUNSUCCESSFULLY.getText());

        Map<String, String> actual = removeBookCommand.execute(request);
        assertEquals(actual, expected);
    }
}
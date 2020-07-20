package test.balashevich.bookapp.controller;

import by.balashevich.bookapp.controller.Controller;
import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseMessage;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.AddBookCommand;
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

@PrepareForTest({AddBookCommand.class, RemoveBookCommand.class})
public class ControllerTest extends PowerMockTestCase {
    Controller controller;

    @BeforeTest
    public void setUp(){
        controller = Controller.getInstance();
    }

    @BeforeMethod
    public void prepareBookStorage() {
        FakeBookStorage.initializeBookLists();
        FakeBookStorage.resetBookStorage();
    }

    @Test
    public void doBookActionSortTest() {
        List<Book> expectedList = FakeBookStorage.getSortedByIdBookList();
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());

        Map<String, String> actual = controller.doBookAction("SORT_ID", null);
        assertEquals(actual, expected);
    }

    @Test
    public void doBookActionFindTest() {
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

        Map<String, String> actual = controller.doBookAction("FIND_TITLE", request);
        assertEquals(actual, expected);
    }

    @Test
    public void doBookActionAddTest() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("bookParams", "title : Master and Margarita; authors : M.Bulgakov; yearPublication : 1928; language : RUSSIAN");

        Book addingBook = new Book("Master and Margarita",
                new ArrayList<>(Arrays.asList("M.Bulgakov")),1928, Language.RUSSIAN);
        List<Book> expectedList = FakeBookStorage.getBaseBookList();
        expectedList.add(addingBook);
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());
        expected.put(ResponseParameterType.BOOK_STORAGE.getName(), expectedList.toString());
        expected.put(ResponseParameterType.MESSAGE.getName(), ResponseMessage.ADDSUCCESSFULLY.getText());

        BookCreator bookCreator = PowerMock.createMock(BookCreator.class);
        PowerMock.expectNew(BookCreator.class).andReturn(bookCreator).anyTimes();
        EasyMock.expect(bookCreator.createBook(EasyMock.anyString())).andReturn(Optional.of(addingBook)).anyTimes();
        PowerMock.replayAll();

        Map<String, String> actual = controller.doBookAction("ADD_BOOK", request);
        assertEquals(actual, expected);
    }

    @Test
    public void doBookActionRemoveTest() throws Exception {
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

        Map<String, String> actual = controller.doBookAction("REMOVE_BOOK", request);
        assertEquals(actual, expected);
    }
}
package test.balashevich.bookapp.controller;

import by.balashevich.bookapp.controller.CommandProvider;
import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.controller.command.imp.EmptyCommand;
import by.balashevich.bookapp.controller.command.imp.SortByIdCommand;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {
    CommandProvider commandProvider;

    @BeforeTest
    public void setUp() {
        commandProvider = new CommandProvider();
    }

    @Test
    public void defineCommandPositive() {
        ActionCommand actual = commandProvider.defineCommand("SORT_ID");
        assertTrue(actual instanceof SortByIdCommand);
    }

    @Test
    public void defineCommandEmpty() {
        ActionCommand actual = commandProvider.defineCommand("");
        assertTrue(actual instanceof EmptyCommand);
    }
}
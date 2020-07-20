package test.balashevich.bookapp.controller.command.impl;

import by.balashevich.bookapp.controller.command.PagePath;
import by.balashevich.bookapp.controller.command.ResponseParameterType;
import by.balashevich.bookapp.controller.command.imp.EmptyCommand;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class EmptyCommandTest {
    EmptyCommand emptyCommand;

    @BeforeTest
    public void setUp() {
        emptyCommand = new EmptyCommand();
    }

    @Test
    public void executeTest() {
        Map<String, String> expected = new HashMap<>();
        expected.put(ResponseParameterType.PAGE.getName(), PagePath.MAIN.getPath());

        Map<String, String> actual = emptyCommand.execute(new HashMap<>());
        assertEquals(actual, expected);
    }
}
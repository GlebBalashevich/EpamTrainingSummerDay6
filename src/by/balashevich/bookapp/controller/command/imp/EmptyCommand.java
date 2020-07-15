package by.balashevich.bookapp.controller.command.imp;

import by.balashevich.bookapp.controller.command.ActionCommand;
import by.balashevich.bookapp.util.ConfigurationManager;

import java.util.HashMap;
import java.util.Map;

import static by.balashevich.bookapp.controller.command.ResponseParameterType.PAGE;

public class EmptyCommand implements ActionCommand {
    private static final String PAGE_MAIN = "page.path.main";

    @Override
    public Map<String, String> execute(Map<String, String> actionParameters) {
        Map<String, String> executeResult = new HashMap<>();
        executeResult.put(PAGE.getName(), ConfigurationManager.getProperty(PAGE_MAIN));
        return executeResult;
    }
}

package by.balashevich.bookapp.controller;

import by.balashevich.bookapp.exception.CommandApplicationException;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

    public Map<String, Object> doBookAction(String commandType, Map<String, Object> actionParameters) {
        Map<String, Object> actionResult = new HashMap<>();

        try {
            ActionCommand actionCommand = CommandProvider.defineCommand(commandType);
            actionResult = actionCommand.execute(actionParameters);
        } catch (CommandApplicationException e) {
            actionResult.put("errorPage", e.getMessage());
        }

        return actionResult;
    }
}

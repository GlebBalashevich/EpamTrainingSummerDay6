package by.balashevich.bookapp.controller;

import by.balashevich.bookapp.exception.CommandApplicationException;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    public Map<String, String> doBookAction(String commandType, Map<String, String> actionParameters) {
        Map<String, String> actionResult = new HashMap<>();

        try {
            CommandProvider command = new CommandProvider();
            ActionCommand actionCommand = command.defineCommand(commandType);
            actionResult = actionCommand.execute(actionParameters);
        } catch (CommandApplicationException e) {
            actionResult.put("errorPage", "Error in application" + e.getMessage());
        }

        // FIXME: 14.07.2020 refactor that method change type, eception 

        return actionResult;
    }
}

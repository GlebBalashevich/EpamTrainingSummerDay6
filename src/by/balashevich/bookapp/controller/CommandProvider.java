package by.balashevich.bookapp.controller;

import by.balashevich.bookapp.exception.CommandApplicationException;

public class CommandProvider {

    public ActionCommand defineCommand(String command) throws CommandApplicationException {
        ActionCommand definedCommand = null;

        if (command != null && !command.isBlank()){
            try{
                definedCommand = CommandType.valueOf(command.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e){
                throw new CommandApplicationException("Error while define command", e);
            }
        }

        return definedCommand;
    }
}

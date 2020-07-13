package by.balashevich.bookapp.controller;

import by.balashevich.bookapp.exception.CommandApplicationException;

import java.util.Map;

public interface ActionCommand {
    Map<String, Object> execute(Map<String, Object> actionParameters) throws CommandApplicationException;
}

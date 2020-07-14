package by.balashevich.bookapp.controller;

import by.balashevich.bookapp.exception.CommandApplicationException;

import java.util.Map;

public interface ActionCommand {
    Map<String, String> execute(Map<String, String> actionParameters) throws CommandApplicationException;
}

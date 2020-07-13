package by.balashevich.bookapp.exception;

public class CommandApplicationException extends Exception{
    public CommandApplicationException() {
        super();
    }

    public CommandApplicationException(String message) {
        super(message);
    }

    public CommandApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandApplicationException(Throwable cause) {
        super(cause);
    }
}

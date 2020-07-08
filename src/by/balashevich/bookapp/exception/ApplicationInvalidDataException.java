package by.balashevich.bookapp.exception;

public class ApplicationInvalidDataException extends Exception{
    public ApplicationInvalidDataException() {
        super();
    }

    public ApplicationInvalidDataException(String message) {
        super(message);
    }

    public ApplicationInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationInvalidDataException(Throwable cause) {
        super(cause);
    }
}

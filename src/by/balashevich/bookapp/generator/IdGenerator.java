package by.balashevich.bookapp.generator;

public class IdGenerator {
    private static final long MIN_ID = 1;
    private static final long MAX_ID = 10_000;

    private static long id;

    private IdGenerator() {
    }

    public static long generateId(){
        id++;

        if (id >= MAX_ID) {
            id = MIN_ID;
        }

        return id;
    }
}

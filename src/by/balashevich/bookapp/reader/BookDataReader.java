package by.balashevich.bookapp.reader;

import by.balashevich.bookapp.exception.ApplicationInvalidDataException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDataReader {
    private static final String DEFAULT_PATH = "input/bookData.txt";

    public List<String> readFileData(String filename) throws ApplicationInvalidDataException {
        Path path;
        List<String> dataLines;

        if (filename != null && Files.exists(Paths.get(filename))) {
            path = Paths.get(filename);
        } else {
            path = Paths.get(DEFAULT_PATH);
        }

        try (Stream<String> lineStream = Files.lines(path)) {
            dataLines = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new ApplicationInvalidDataException("Exception while opening file", e);
        }

        return dataLines;
    }
}

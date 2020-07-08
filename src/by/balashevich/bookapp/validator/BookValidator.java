package by.balashevich.bookapp.validator;

import by.balashevich.bookapp.entity.Language;

import java.time.Year;
import java.util.List;

public class BookValidator {
    private static final int MAX_TITLE_LENGTH = 255;
    private static final int MAX_AUTHOR_LENGTH = 40;
    private static final int MAX_AUTHORS_NUMBER = 8;
    private static final String WORD_CHARACTER = "\\w";

    public boolean validateBookElements(String title, List<String> authors, int yearPublication, Language language) {
        return validateTitle(title) &&
                validateAuthors(authors) &&
                validateYearPublication(yearPublication) && language != null;
    }

    public boolean validateTitle(String title) {
        boolean isValid = false;

        if (!title.isBlank()) {
            if (title.length() > 0 && title.length() <= MAX_TITLE_LENGTH) {
                if (title.matches(WORD_CHARACTER)) {
                    isValid = true;
                }
            }
        }

        return isValid;
    }

    public boolean validateAuthors(List<String> authors) {
        boolean isValid = false;

        if (!authors.isEmpty()) {
            if (authors.size() < MAX_AUTHORS_NUMBER) {
                int correctAuthorsIndex = 0;

                for (String author : authors) {
                    if (validateSingleAuthor(author)){
                        correctAuthorsIndex++;
                    }
                }

                isValid = correctAuthorsIndex == authors.size();
            }
        }

        return isValid;
    }

    public boolean validateSingleAuthor(String author) {
        boolean isValid = false;

        if (!author.isEmpty()) {
            if (author.length() < MAX_AUTHOR_LENGTH && author.matches(WORD_CHARACTER)) {
                isValid = true;
            }
        }

        return isValid;
    }

    public boolean validateYearPublication(int yearPublication) {

        return (yearPublication > 0) && (yearPublication < Year.now().getValue());
    }
}

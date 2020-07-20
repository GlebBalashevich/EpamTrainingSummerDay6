package test.balashevich.bookapp.model.entity;

import by.balashevich.bookapp.model.entity.Book;
import by.balashevich.bookapp.model.entity.BookStorage;
import by.balashevich.bookapp.model.entity.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeBookStorage {
    private static BookStorage bookStorage;
    private static List<Book> baseBookList;
    private static List<Book> sortedByIdBookList;
    private static List<Book> sortedByTitleBookList;
    private static List<Book> sortedByAuthorBookList;
    private static List<Book> sortedByYearPublicationBookList;
    private static List<Book> sortedByLanguageBookList;

    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;
    private static Book book5;
    private static Book book6;
    private static Book book7;
    private static Book book8;
    private static Book book9;
    private static Book book10;

    static {
        book1 = new Book("The Lord Of The Rings",
                new ArrayList<>(Arrays.asList("J.R.R.Tolkien")), 1956, Language.ENGLISH);
        book2 = new Book("Good Signs",
                new ArrayList<>(Arrays.asList("T.Pratchett", "N.Gaiman")), 2010, Language.ENGLISH);
        book3 = new Book("1984",
                new ArrayList<>(Arrays.asList("J.Oruel")), 1949, Language.ENGLISH);
        book4 = new Book("12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1925, Language.RUSSIAN);
        book5 = new Book("12 Cheers",
                new ArrayList<>(Arrays.asList("I.Ilf", "E.Petrov")), 1940, Language.ENGLISH);
        book6 = new Book("Faust",
                new ArrayList<>(Arrays.asList("I.V.Goethe")), 1808, Language.GERMAN);
        book7 = new Book("Roadside Picnic",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1956, Language.RUSSIAN);
        book8 = new Book("Monday starts at Saturday",
                new ArrayList<>(Arrays.asList("A.Strugatsky", "B.Strugatsky")), 1950, Language.RUSSIAN);
        book9 = new Book("Divine Comedy",
                new ArrayList<>(Arrays.asList("A.Dante")), 1265, Language.ITALIAN);
        book10 = new Book("Process",
                new ArrayList<>(Arrays.asList("F.Kafka")), 1925, Language.GERMAN);
    }

    private FakeBookStorage() {
    }

    public static BookStorage resetBookStorage() {
        bookStorage = BookStorage.getInstance();

        List<Book> books = new ArrayList<>(bookStorage.getBooks());
        cleanStorage(books);
        fillStorage();

        return bookStorage;
    }

    public static void cleanStorage(List<Book> books) {
        if (!books.isEmpty()) {
            for (Book book : books) {
                bookStorage.removeBook(book);
            }
        }
    }

    public static void fillStorage() {
        for (Book book : baseBookList) {
            bookStorage.addBook(book);
        }
    }

    public static void initializeBookLists() {
        baseBookList = new ArrayList<>();
        sortedByIdBookList = new ArrayList<>();
        sortedByTitleBookList = new ArrayList<>();
        sortedByAuthorBookList = new ArrayList<>();
        sortedByYearPublicationBookList = new ArrayList<>();
        sortedByLanguageBookList = new ArrayList<>();

        baseBookList.add(book3);
        baseBookList.add(book5);
        baseBookList.add(book1);
        baseBookList.add(book9);
        baseBookList.add(book8);
        baseBookList.add(book10);
        baseBookList.add(book2);
        baseBookList.add(book4);
        baseBookList.add(book7);
        baseBookList.add(book6);

        sortedByIdBookList.add(book1);
        sortedByIdBookList.add(book2);
        sortedByIdBookList.add(book3);
        sortedByIdBookList.add(book4);
        sortedByIdBookList.add(book5);
        sortedByIdBookList.add(book6);
        sortedByIdBookList.add(book7);
        sortedByIdBookList.add(book8);
        sortedByIdBookList.add(book9);
        sortedByIdBookList.add(book10);

        sortedByTitleBookList.add(book5);
        sortedByTitleBookList.add(book4);
        sortedByTitleBookList.add(book3);
        sortedByTitleBookList.add(book9);
        sortedByTitleBookList.add(book6);
        sortedByTitleBookList.add(book2);
        sortedByTitleBookList.add(book8);
        sortedByTitleBookList.add(book10);
        sortedByTitleBookList.add(book7);
        sortedByTitleBookList.add(book1);

        sortedByAuthorBookList.add(book9);
        sortedByAuthorBookList.add(book8);
        sortedByAuthorBookList.add(book7);
        sortedByAuthorBookList.add(book5);
        sortedByAuthorBookList.add(book4);
        sortedByAuthorBookList.add(book10);
        sortedByAuthorBookList.add(book6);
        sortedByAuthorBookList.add(book3);
        sortedByAuthorBookList.add(book1);
        sortedByAuthorBookList.add(book2);

        sortedByYearPublicationBookList.add(book9);
        sortedByYearPublicationBookList.add(book6);
        sortedByYearPublicationBookList.add(book10);
        sortedByYearPublicationBookList.add(book4);
        sortedByYearPublicationBookList.add(book5);
        sortedByYearPublicationBookList.add(book3);
        sortedByYearPublicationBookList.add(book8);
        sortedByYearPublicationBookList.add(book1);
        sortedByYearPublicationBookList.add(book7);
        sortedByYearPublicationBookList.add(book2);

        sortedByLanguageBookList.add(book3);
        sortedByLanguageBookList.add(book5);
        sortedByLanguageBookList.add(book1);
        sortedByLanguageBookList.add(book2);
        sortedByLanguageBookList.add(book10);
        sortedByLanguageBookList.add(book6);
        sortedByLanguageBookList.add(book9);
        sortedByLanguageBookList.add(book8);
        sortedByLanguageBookList.add(book4);
        sortedByLanguageBookList.add(book7);
    }

    public static List<Book> getBaseBookList() {
        return baseBookList;
    }

    public static List<Book> getSortedByIdBookList() {
        return sortedByIdBookList;
    }

    public static List<Book> getSortedByTitleBookList() {
        return sortedByTitleBookList;
    }

    public static List<Book> getSortedByAuthorBookList() {
        return sortedByAuthorBookList;
    }

    public static List<Book> getSortedByYearPublicationBookList() {
        return sortedByYearPublicationBookList;
    }

    public static List<Book> getSortedByLanguageBookList() {
        return sortedByLanguageBookList;
    }
}

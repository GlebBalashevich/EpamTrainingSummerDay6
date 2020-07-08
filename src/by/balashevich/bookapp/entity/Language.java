package by.balashevich.bookapp.entity;

public enum Language {
    RUSSIAN("Russian"),
    ENGLISH("English"),
    CHINESE("Chinese"),
    GERMAN("German"),
    ITALIAN("Italian");

    private final String name;

    private Language(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

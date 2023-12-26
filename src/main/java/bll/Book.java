package bll;

import data.CsvReader;
import data.CsvWriter;

import java.util.List;

public class Book {
    private String title;
    private String isbn;
    private Status status;

    public Book(String title, String isbn, Status status) {
        this.title = title;
        this.isbn = isbn;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Status getStatus() {
        return status;
    }

    public static void writeData(Book book) {
        CsvWriter.writeBookToCsv("books.csv", book);
    }

    public static List<Book> readData() {
        return CsvReader.readBooksFromCsv("books.csv");
    }
}

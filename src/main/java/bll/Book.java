package bll;

import data.CsvReader;
import data.CsvWriter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int pages;
    private Date publicationDate;
    private Status status;

    public Book(int id, String title, String author, String isbn, int pages, Date publicationDate, Status status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.status = status;
    }
    public Book(String title, String author, String isbn, int pages, Date publicationDate, Status status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public int getId(){
        return id;
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

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static void writeData(Book book) {
        CsvWriter.writeBookToCsv("books.csv", book);
    }
    public static void writeData(List<Book> books) {
        CsvWriter.writeBooksToCsv("books.csv", books);
    }

    public static List<Book> readData() {
        return CsvReader.readBooksFromCsv("books.csv");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && pages == book.pages && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(publicationDate, book.publicationDate) && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, pages, publicationDate, status);
    }
}

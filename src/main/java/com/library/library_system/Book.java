package com.library.library_system;

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
}

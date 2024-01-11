package dao;

import bll.Book;
import bll.Status;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private List<Book> books;

    public BookDao() {
        this.books = Book.readData();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void insert(Book book) {
        books.add(book);
        Book.writeData(book);
        books = Book.readData();
    }

    public void delete(Book book) {
        books.remove(book);
        Book.writeData(books);
        books = Book.readData();
    }

    public void edit(Book book) {
        books.remove(getBookById(book.getId()));
        insert(book);
    }

    public Book getBookById(int id) {
        Book book = null;
        for (Book b:books) {
            if (b.getId() == id){
                book=b;
            }
        }
        return book;
    }

    public List<Book> getBooksByStatus(Status status) {
        List<Book> stBooks = new ArrayList<Book>();
        for (Book book: books) {
            if(book.getStatus()==status) {
                stBooks.add(book);
            }
        }
        return stBooks;
    }
}

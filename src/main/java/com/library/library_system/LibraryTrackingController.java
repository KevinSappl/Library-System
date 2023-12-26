package com.library.library_system;

import bll.Book;
import data.CsvReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryTrackingController implements Initializable {
    @FXML
    TableView<Book> tvBooks;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<Book, String> isbnColumn;
    @FXML
    TableColumn<Book, String> statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = CsvReader.readBooksFromCsv("books.csv");
        tvBooks.getItems().setAll(books);
    }

}

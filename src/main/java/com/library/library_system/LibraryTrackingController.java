package com.library.library_system;

import bll.Book;
import data.CsvReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryTrackingController implements Initializable {
    @FXML
    TableView<Book> tvBooks;
    @FXML
    TableColumn<Book, String> titleColumn;
    @FXML
    TableColumn<Book, String> authorColumn;
    @FXML
    TableColumn<Book, String> isbnColumn;
    @FXML
    TableColumn<Book, String> pagesColumn;
    @FXML
    TableColumn<Book, Date> publicationDateColumn;
    @FXML
    TableColumn<Book, String> statusColumn;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        publicationDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(sdf.format(item));
                }
            }
        });

        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = CsvReader.readBooksFromCsv("books.csv");
        tvBooks.getItems().setAll(books);
    }

}

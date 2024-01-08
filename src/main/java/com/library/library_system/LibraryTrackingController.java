package com.library.library_system;

import bll.Book;
import data.CsvReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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

        tvBooks.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Book selectedBook = row.getItem();
                    openEditWindow(selectedBook);
                }
            });
            return row;
        });
    }

    private void loadBooks() {
        List<Book> books = CsvReader.readBooksFromCsv("books.csv");
        tvBooks.getItems().setAll(books);
    }

    private void openEditWindow(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library-editBook-view.fxml"));
            Parent root = loader.load();

            LibraryEditBookController editBookController = loader.getController();
            editBookController.setBookDetails(book);

            Stage editStage = new Stage();
            editStage.setScene(new Scene(root));
            editStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

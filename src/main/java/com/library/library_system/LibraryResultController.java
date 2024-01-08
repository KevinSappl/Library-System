package com.library.library_system;

import bll.Book;
import data.CsvReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryResultController implements Initializable {
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
    @FXML
    TableColumn editColumn;
    @FXML
    TableColumn deleteColumn;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private String text;

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

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> editCellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            // Hier Aktion mit dem Objekt data ausführen
                            openEditWindow(book);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        editColumn.setCellFactory(editCellFactory);

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> deleteCellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book data = getTableView().getItems().get(getIndex());
                            // Hier Aktion mit dem Objekt data ausführen
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        deleteColumn.setCellFactory(deleteCellFactory);

        loadBooks();
    }

    private void loadBooks() {
        List<Book> availableBooks = CsvReader.readBooksFromCsvByTitle("books.csv", text);
        tvBooks.getItems().setAll(availableBooks);

    }

    public void setTitle(String text) {
        this.text = text;
        loadBooks();
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


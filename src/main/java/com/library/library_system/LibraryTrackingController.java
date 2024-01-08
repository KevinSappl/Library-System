package com.library.library_system;

import bll.Book;
import bll.Status;
import data.CsvReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryTrackingController implements Initializable {
    @FXML
    TableView<Book> tvAvailableBooks;
    @FXML
    TableColumn<Book, String> availableTitleColumn;
    @FXML
    TableColumn<Book, String> availableAuthorColumn;
    @FXML
    TableColumn<Book, String> availableIsbnColumn;
    @FXML
    TableColumn<Book, String> availablePagesColumn;
    @FXML
    TableColumn<Book, Date> availablePublicationDateColumn;
    @FXML
    TableColumn<Book, String> availableStatusColumn;
    @FXML
    TableColumn availableEditColumn;
    @FXML
    TableColumn availableDeleteColumn;
    @FXML
    TableView<Book> tvBorrowedBooks;
    @FXML
    TableColumn<Book, String> borrowedTitleColumn;
    @FXML
    TableColumn<Book, String> borrowedAuthorColumn;
    @FXML
    TableColumn<Book, String> borrowedIsbnColumn;
    @FXML
    TableColumn<Book, String> borrowedPagesColumn;
    @FXML
    TableColumn<Book, Date> borrowedPublicationDateColumn;
    @FXML
    TableColumn<Book, String> borrowedStatusColumn;
    @FXML
    TableColumn borrowedEditColumn;
    @FXML
    TableColumn borrowedDeleteColumn;
    @FXML
    TableView<Book> tvUnavailableBooks;
    @FXML
    TableColumn<Book, String> unavailableTitleColumn;
    @FXML
    TableColumn<Book, String> unavailableAuthorColumn;
    @FXML
    TableColumn<Book, String> unavailableIsbnColumn;
    @FXML
    TableColumn<Book, String> unavailablePagesColumn;
    @FXML
    TableColumn<Book, Date> unavailablePublicationDateColumn;
    @FXML
    TableColumn<Book, String> unavailableStatusColumn;
    @FXML
    TableColumn unavailableEditColumn;
    @FXML
    TableColumn unavailableDeleteColumn;


    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        availablePagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        availablePublicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        availableStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        availablePublicationDateColumn.setCellFactory(column -> new TableCell<>() {
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

        availableEditColumn.setCellFactory(editCellFactory);
        borrowedEditColumn.setCellFactory(editCellFactory);
        unavailableEditColumn.setCellFactory(editCellFactory);

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

        availableDeleteColumn.setCellFactory(deleteCellFactory);
        borrowedDeleteColumn.setCellFactory(deleteCellFactory);
        unavailableDeleteColumn.setCellFactory(deleteCellFactory);

        borrowedTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowedAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        borrowedIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        borrowedPagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        borrowedPublicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        borrowedStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        borrowedPublicationDateColumn.setCellFactory(column -> new TableCell<>() {
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

        unavailableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        unavailableAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        unavailableIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        unavailablePagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        unavailablePublicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        unavailableStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        unavailablePublicationDateColumn.setCellFactory(column -> new TableCell<>() {
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
        List<Book> availableBooks = CsvReader.readBooksFromCsvByStatus("books.csv", Status.AVAILABLE);
        tvAvailableBooks.getItems().setAll(availableBooks);

        List<Book> borrowedBooks = CsvReader.readBooksFromCsvByStatus("books.csv", Status.BORROWED);
        tvBorrowedBooks.getItems().setAll(borrowedBooks);

        List<Book> unavailableBooks = CsvReader.readBooksFromCsvByStatus("books.csv", Status.UNAVAILABLE);
        tvUnavailableBooks.getItems().setAll(unavailableBooks);

    }

}

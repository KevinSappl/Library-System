package com.library.library_system;

import bll.Book;
import bll.Status;
import dao.BookDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class LibraryEditBookController {

    @FXML
    private TextField tfBookTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfISBN;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Spinner<Integer> spPages;

    @FXML
    private ComboBox<Status> cbState;
    BookDao bookDao = new BookDao();
    Book currentBook;

    public void initialize() {
        ObservableList<Status> statusList = FXCollections.observableArrayList(Status.values());
        cbState.setItems(statusList);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, Integer.MAX_VALUE, 0, 1);

        spPages.setValueFactory(valueFactory);
    }
    public void setBookDetails(Book book) {
        currentBook=book;
        tfBookTitle.setText(book.getTitle());
        tfAuthor.setText(book.getAuthor());
        tfISBN.setText(book.getIsbn());
        dpDate.setValue(book.getPublicationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        spPages.getValueFactory().setValue(book.getPages());
        cbState.setValue(book.getStatus());
    }

    public void saveBook(ActionEvent actionEvent) {
        bookDao.delete(currentBook);
        currentBook.setTitle(tfBookTitle.getText());
        currentBook.setAuthor(tfAuthor.getText());
        currentBook.setIsbn(tfISBN.getText());

        // Konvertieren von LocalDate zu Instant
        ZonedDateTime zonedDateTime = dpDate.getValue().atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();

        // Konvertieren von Instant zu Date
        Date date = Date.from(instant);

        currentBook.setPublicationDate(date);
        currentBook.setPages(spPages.getValue());
        currentBook.setStatus(cbState.getValue());
        bookDao.insert(currentBook);

        Stage stage = (Stage) tfBookTitle.getScene().getWindow();
        stage.close();
    }
}

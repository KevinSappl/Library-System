package com.library.library_system;

import bll.Book;
import bll.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.ZoneId;

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

    public void initialize() {
        ObservableList<Status> statusList = FXCollections.observableArrayList(Status.values());
        cbState.setItems(statusList);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, Integer.MAX_VALUE, 0, 1);

        spPages.setValueFactory(valueFactory);
    }
    public void setBookDetails(Book book) {
        tfBookTitle.setText(book.getTitle());
        tfAuthor.setText(book.getAuthor());
        tfISBN.setText(book.getIsbn());
        dpDate.setValue(book.getPublicationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        spPages.getValueFactory().setValue(book.getPages());
        cbState.setValue(book.getStatus());
    }
}

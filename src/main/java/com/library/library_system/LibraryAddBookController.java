package com.library.library_system;

import bll.Book;
import bll.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LibraryAddBookController implements Initializable {
    @FXML
    TextField tfBookTitle;
    @FXML
    TextField tfAuthor;
    @FXML
    TextField tfISBN;
    @FXML
    DatePicker dpDate;
    @FXML
    Spinner<Integer> spPages;
    @FXML
    Label lbStatus;

    @FXML
    private void onClickAdd(ActionEvent event) {
        if(checkInput()) {
            Book.writeData(new Book(tfBookTitle.getText(), tfAuthor.getText(), tfISBN.getText(), spPages.getValue(), Date.from(dpDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Status.AVAILABLE));
            lbStatus.setText("Book " + tfBookTitle.getText() + " successfully added");
        }
        else {
            lbStatus.setText("Invalid input");
        }
    }

    private boolean checkInput() {
        boolean inputOK = true;
        if(tfBookTitle.getText().isEmpty()) {
            tfBookTitle.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            inputOK = false;
        }
        else {
            tfBookTitle.setStyle("");
        }
        if(tfISBN.getText().isEmpty()) {
            tfISBN.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            inputOK = false;
        }
        else {
            tfISBN.setStyle("");
        }
        return inputOK;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, Integer.MAX_VALUE, 0, 1);

        spPages.setValueFactory(valueFactory);

        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change; // Leere Eingabe erlauben
            }

            try {
                Integer.parseInt(newText);
                return change; // Akzeptieren, wenn es eine gültige Integer-Eingabe ist
            } catch (NumberFormatException e) {
                return null; // Andernfalls die Änderung verwerfen
            }
        });

        spPages.getEditor().setTextFormatter(formatter);

        formatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                spPages.getValueFactory().setValue(newValue);
            }
        });

        spPages.valueProperty().addListener((obs, oldValue, newValue) -> {
            formatter.setValue(newValue);
        });
    }
}

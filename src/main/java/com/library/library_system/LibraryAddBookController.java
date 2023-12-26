package com.library.library_system;

import bll.Book;
import bll.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LibraryAddBookController {
    @FXML
    TextField tfBookTitle;
    @FXML
    TextField tfISBN;
    @FXML
    Label lbStatus;

    @FXML
    private void onClickAdd(ActionEvent event) {
        if(checkInput()) {
            Book.writeData(new Book(tfBookTitle.getText(), tfISBN.getText(), Status.AVAILABLE));
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
}

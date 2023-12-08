package com.library.library_system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LibraryStartController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
package com.library.library_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LibraryLoginController {
    private LibraryStartController startController;

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField pfPassword;
    @FXML
    private Label lbLoginResult;
    private Button btnSearch;
    private Button btnLogin;
    private Button btnLogout;
    private TextField tfSearchBooks;

    public void setStartController(LibraryStartController startController) {
        this.startController = startController;
    }

    public void setSearchField(TextField tfSearchBooks) {
        this.tfSearchBooks = tfSearchBooks;
    }

    public void setSearchButton(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public void setLoginButton(Button btnLogin) {
        this.btnLogin = btnLogin;
    }
    public void setLogoutButton(Button btnLogout) {
        this.btnLogout = btnLogout;
    }
    @FXML
    private void onClickLogin(ActionEvent event) {
        if(tfUsername.getText().equals("username") && pfPassword.getText().equals("password")){
            lbLoginResult.setText("Login Successful");
            tfSearchBooks.setDisable(false);
            btnSearch.setDisable(false);
            btnLogin.setDisable(true);
            btnLogout.setDisable(false);
        }else{
            lbLoginResult.setText("username or password wrong");
        }
    }
}

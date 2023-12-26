package com.library.library_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryStartController {

    private LibraryLoginController loginController;

    public void setLoginController(LibraryLoginController loginController) {
        this.loginController = loginController;
    }

    @FXML
    private TextField tfSearchBooks;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnLogout;
    @FXML
    public Button btnTracking;
    @FXML
    public Button btnAddBook;

    @FXML
    private void onClickTracking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library-tracking-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Tracking View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickAddBook(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library-addBook-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Book View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library-login-view.fxml"));
            Parent root = loader.load();
            LibraryLoginController controller = loader.getController();
            controller.setStartController(this); // Setze eine Referenz auf den LibraryStartController im LibraryLoginController
            controller.setSearchButton(btnSearch);
            controller.setSearchField(tfSearchBooks);
            controller.setLoginButton(btnLogin);
            controller.setLogoutButton(btnLogout);
            controller.setTrackingButton(btnTracking);
            controller.setAddBookButton(btnAddBook);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickSearch(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library-result-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Result View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClickLogout(ActionEvent event) {
        btnSearch.setDisable(true);
        tfSearchBooks.setDisable(true);
        btnLogin.setDisable(false);
        btnLogout.setDisable(true);
        tfSearchBooks.setDisable(false);
        btnSearch.setDisable(false);
        btnTracking.setDisable(false);
        btnAddBook.setDisable(false);
    }
}
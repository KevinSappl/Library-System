module com.library.library_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.library.library_system to javafx.fxml;
    exports com.library.library_system;
}
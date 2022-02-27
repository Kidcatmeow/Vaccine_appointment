module com.example.projectgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.projectgui to javafx.fxml;
    exports com.example.projectgui;
}
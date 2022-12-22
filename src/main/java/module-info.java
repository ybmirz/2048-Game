module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.example.demo.Controller to javafx.fxml;
    opens com.example.demo.Dialogs to javafx.fxml;
    exports com.example.demo;
}   
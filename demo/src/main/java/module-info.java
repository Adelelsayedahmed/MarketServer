module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.guiclient to javafx.fxml;
    exports com.example.guiclient;
}
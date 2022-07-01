module com.example.guiadmin {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.guiadmin to javafx.fxml;
    exports com.example.guiadmin;
}
module com.example.lotto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.lotto to javafx.fxml;
    exports com.example.lotto;
}
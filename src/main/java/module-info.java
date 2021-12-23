module com.example.javafxtableexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;

    opens com.example.javafxtableexample to javafx.fxml;
    exports com.example.javafxtableexample;

}
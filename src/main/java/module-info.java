module com.example.hamburguesassimulador {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.hamburguesassimulador to javafx.fxml;
    exports com.example.hamburguesassimulador;
}
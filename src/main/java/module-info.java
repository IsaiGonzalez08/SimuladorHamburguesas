module com.example.hamburguesassimulador {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.hamburguesassimulador to javafx.fxml;
    exports com.example.hamburguesassimulador;
    exports com.example.hamburguesassimulador.Controllers;
    opens com.example.hamburguesassimulador.Controllers to javafx.fxml;
    exports com.example.hamburguesassimulador.Threads;
    opens com.example.hamburguesassimulador.Threads to javafx.fxml;
}
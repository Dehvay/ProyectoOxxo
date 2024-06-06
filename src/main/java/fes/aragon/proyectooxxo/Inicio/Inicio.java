package fes.aragon.proyectooxxo.Inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Inicio extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("/fes/aragon/proyectooxxo/xml/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PUNTO DE VENTA OXXO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
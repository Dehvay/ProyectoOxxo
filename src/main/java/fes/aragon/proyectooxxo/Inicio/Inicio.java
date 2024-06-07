package fes.aragon.proyectooxxo.Inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class Inicio extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("/fes/aragon/proyectooxxo/xml/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SISTEMA DE INVENTARIO OXXO");
        try (InputStream iconStream = getClass().getResourceAsStream("/fes/aragon/proyectooxxo/imagen/Oxxo_Logo_Icono.png")) {
            if (iconStream == null) {
                throw new IOException("Icono no encontrado en la ruta especificada.");
            }
            Image icono = new Image(iconStream);
            stage.getIcons().add(icono);
        } catch (IOException e) {
            System.err.println("Error al cargar el ícono: " + e.getMessage());
        }

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
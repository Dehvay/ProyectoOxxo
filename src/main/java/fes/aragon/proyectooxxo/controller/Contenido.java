package fes.aragon.proyectooxxo.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class Contenido extends BorderPane {
    public Contenido(String ruta) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }
}

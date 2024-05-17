package fes.aragon.proyectooxxo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class InicioController {

    @FXML
    private BorderPane btpPrincipal;
    @FXML
    private Button idProductos;

    @FXML
    private Button idProveedores;

    @FXML
    private Button idVentas;

    @FXML
    void eventoAbrirProductos(ActionEvent event) {
        ventana("/fes/aragon/proyectooxxo/xml/productos.fxml");
    }

    @FXML
    void eventoAbrirProveedores(ActionEvent event) {
        ventana("/fes/aragon/proyectooxxo/xml/proveedores.fxml");
    }

    @FXML
    void eventoAbrirVentas(ActionEvent event) {
        ventana("/fes/aragon/proyectooxxo/xml/ventas.fxml");
    }
    ////////////////
    private void ventana(String ruta){
        try{
            Contenido contenido = new Contenido(ruta);
            btpPrincipal.setCenter(contenido);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}

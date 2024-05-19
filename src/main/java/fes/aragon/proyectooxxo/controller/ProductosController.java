package fes.aragon.proyectooxxo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ProductosController {

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TableColumn<?, ?> cmlFechaCaducidad;

    @FXML
    private TableColumn<?, ?> cmlImagen;

    @FXML
    private TableColumn<?, ?> cmlNombre;

    @FXML
    private TableColumn<?, ?> cmlOperaciones;

    @FXML
    private TableColumn<?, ?> cmlPrecioUnitario;

    @FXML
    private TableColumn<?, ?> cmlPrecioVenta;

    @FXML
    private TableColumn<?, ?> cmlProveedor;

    @FXML
    private Button idArchivoProductos;

    @FXML
    private Button idGuardarProductos;

    @FXML
    void eventoAgregarProducto(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_producto.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void eventoArchivoProductos(ActionEvent event) {

    }

    @FXML
    void eventoGuardarProductos(ActionEvent event) {

    }

}

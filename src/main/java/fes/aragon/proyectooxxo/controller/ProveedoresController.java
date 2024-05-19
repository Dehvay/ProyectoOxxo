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

public class ProveedoresController {

    @FXML
    private TableColumn<?, ?> cmlCorreo;

    @FXML
    private TableColumn<?, ?> cmlDireccion;

    @FXML
    private TableColumn<?, ?> cmlImagen;

    @FXML
    private TableColumn<?, ?> cmlNombre;

    @FXML
    private TableColumn<?, ?> cmlOperaciones;

    @FXML
    private TableColumn<?, ?> cmlTelefono;

    @FXML
    private Button idAgregarProveedor;

    @FXML
    private Button idArchivoProveedor;

    @FXML
    private Button idGuardarProveedor;

    @FXML
    void eventoAgregarProveedor(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_proveedor.fxml"));
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
    void eventoArchivoProveedor(ActionEvent event) {

    }

    @FXML
    void eventoGuardarProveedor(ActionEvent event) {

    }

}

package fes.aragon.proyectooxxo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class VentasController {

    @FXML
    private TableColumn<?, ?> clmIDProducto;

    @FXML
    private TableColumn<?, ?> clmNombre;

    @FXML
    private TableColumn<?, ?> cmlCantidad;

    @FXML
    private TableColumn<?, ?> cmlPrecioUnitario;

    @FXML
    private Button idAgregarProducto;

    @FXML
    private Button idCancelarProducto;

    @FXML
    private Button idCancelarVenta;

    @FXML
    private Button idCobrar;

    @FXML
    private TextField txfCantidad;

    @FXML
    private TextField txfNombreProducto;

    @FXML
    private TextField txfTotal;

    @FXML
    void eventoAgregarProducto(ActionEvent event) {

    }

    @FXML
    void eventoCancelarProducto(ActionEvent event) {

    }

    @FXML
    void eventoCancelarVenta(ActionEvent event) {

    }

    @FXML
    void eventoCobrar(ActionEvent event) {

    }

}

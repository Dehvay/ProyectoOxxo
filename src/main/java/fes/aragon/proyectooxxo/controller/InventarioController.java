package fes.aragon.proyectooxxo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InventarioController {

    @FXML
    private TableColumn<?, ?> clmCantidad;

    @FXML
    private TableColumn<?, ?> clmCategoria;

    @FXML
    private TableColumn<?, ?> clmIdProducto;

    @FXML
    private TableColumn<?, ?> clmNombreProducto;

    @FXML
    private TableColumn<?, ?> clmPrecioUnitario;

    @FXML
    private TableColumn<?, ?> clmPrecioVenta;

    @FXML
    private TableColumn<?, ?> clmProveedor;

    @FXML
    private TableColumn<?, ?> clmValorInventario;

    @FXML
    private Button idbtnAbrirInventario;

    @FXML
    private Button idbtnAgregarProdI;

    @FXML
    private Button idbtnGuardarInvent;

    @FXML
    private TableView<?> tblInventario;

    @FXML
    void eventoAbrirInventario(ActionEvent event) {

    }

    @FXML
    void eventoAgregarProdaInventario(ActionEvent event) {

    }

    @FXML
    void eventoGuardarInventario(ActionEvent event) {

    }

}

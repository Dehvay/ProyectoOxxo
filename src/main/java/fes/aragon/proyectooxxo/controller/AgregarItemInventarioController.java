package fes.aragon.proyectooxxo.controller;

import fes.aragon.proyectooxxo.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import fes.aragon.proyectooxxo.modelo.SinglentonItemInventario;

import java.io.File;

public class AgregarItemInventarioController {

    @FXML
    private ComboBox<Proveedor> ComboBoxProveedor;

    @FXML
    private Button btnGuardarProductoInv;

    @FXML
    private TextField cantidadtxt;

    @FXML
    private TextField categoriatxt;

    @FXML
    private ComboBox<Producto> comboBoxProducto;

    private File selectedFIle;
    private Integer indice;


    public void initialize(){
        SinglentonProductos singlentonProductos = SinglentonProductos.getInstance();
        comboBoxProducto.setItems(singlentonProductos.getLista());
        comboBoxProducto.setConverter(new StringConverter<>() {
            @Override
            public String toString(Producto producto) {
                return producto.getNombre();
            }

            @Override
            public Producto fromString(String s) {
                return null;
            }
        });

        SinglentonProveedores singlentonProveedores = SinglentonProveedores.getInstance();
        ComboBoxProveedor.setItems(singlentonProveedores.getLista());
        ComboBoxProveedor.setConverter(new StringConverter<>() {
            @Override
            public String toString(Proveedor proveedor) {
                return proveedor.getNombre();
            }

            @Override
            public Proveedor fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void guardarItemInventario(ActionEvent event) {
        ItemInventario itemInventario = new ItemInventario();
        itemInventario.setProductoI(comboBoxProducto.getSelectionModel().getSelectedItem());
        itemInventario.setProveedorI(ComboBoxProveedor.getSelectionModel().getSelectedItem());
        itemInventario.setCantidadIngresada(Integer.parseInt(cantidadtxt.getText()));
        itemInventario.setCategoria(categoriatxt.getText());

        if(indice == null){
            SinglentonItemInventario.getInstance().getLista().add(itemInventario);
        }else{
            SinglentonItemInventario.getInstance().getLista().set(indice, itemInventario);
        }

        limpiarCampos();
    }

    private void limpiarCampos() {
        comboBoxProducto.getSelectionModel().clearSelection();
        ComboBoxProveedor.getSelectionModel().clearSelection();
        cantidadtxt.clear();
        categoriatxt.clear();
    }

    public void indiceItems(int indice){
        this.indice = indice;
        ItemInventario itemInventario = SinglentonItemInventario.getInstance().getLista().get(indice);
        comboBoxProducto.setValue(itemInventario.getProductoI());
        ComboBoxProveedor.setValue(itemInventario.getProveedorI());
        cantidadtxt.setText(String.valueOf(itemInventario.getCantidadIngresada()));
        categoriatxt.setText(itemInventario.getCategoria());

    }
}

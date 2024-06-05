package fes.aragon.proyectooxxo.controller;

import fes.aragon.proyectooxxo.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AgregarProductoController implements Initializable {

    @FXML
    private Button btnAbrirImagen;
    @FXML
    private TextField caducidadtxt;

    @FXML
    private TextField cantidadtxt;

    @FXML
    private Button guardarbtn;

    @FXML
    private ImageView imgProducto;
    @FXML
    private TextField nombretxt;

    @FXML
    private TextField preciodeventatxt;

    @FXML
    private TextField preciounitariotxt;
    @FXML
    private TextField txfIdProducto;
    @FXML
    private ComboBox<Proveedor> cmbProveedores;
    ///////////////
    private Integer indice;
    private File selectedFile;
    private ObservableList<Producto> listaGeneral;

    @FXML
    void abrirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("imagen", "*.png", "*.jpeg"));
        this.selectedFile = fileChooser.showOpenDialog(this.guardarbtn.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Image imagen = new Image(fileInputStream);
                this.imgProducto.setImage(imagen);
            } catch (IOException e) {
                mensajeError("Error al abrir la imagen", "No se puedo cargar la imagen.");
            }
        }
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        String nombreProducto = nombretxt.getText();
        if (nombresDuplicados(nombreProducto)){
            mensajeError("Error", "El nombre del producto ya esta en uso. Elige otro nombre");
            return;
        }
        //Verificar id de Productos
        int nuevoId = Integer.parseInt(txfIdProducto.getText());
        boolean idDuplicado = idDuplicados(nuevoId);
        if (idDuplicado){
            mensajeError("Error: ID duplicado","Este ID ya esta en uso, Elige otro ID.");
        }else {
            //Guardado de Producto
            Producto producto = new Producto();
            producto.setNombre(nombretxt.getText());
            producto.setIdP(Integer.parseInt(txfIdProducto.getText()));
            producto.setCantidad(Integer.parseInt(cantidadtxt.getText()));
            producto.setFechaDeCaducidad(caducidadtxt.getText());
            producto.setPrecioDeVenta(Double.parseDouble(preciodeventatxt.getText()));
            producto.setPrecioUnitario(Double.parseDouble(preciounitariotxt.getText()));
            //Asignar Proveedor
            producto.setProveedor(cmbProveedores.getSelectionModel().getSelectedItem());
            if (selectedFile != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    Image imagen = new Image(fileInputStream);
                    SerializableImage imag = new SerializableImage();
                    imag.setImage(imagen);
                    producto.setImagen(imag);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (indice == null) {
                SinglentonProductos.getInstance().getLista().add(producto);
            } else {
                Image image = this.imgProducto.getImage();
                SerializableImage image1 = new SerializableImage();
                image1.setImage(image);
                producto.setImagen(image1);
                SinglentonProductos.getInstance().getLista().set(indice, producto);
                Stage stage = (Stage) this.guardarbtn.getScene().getWindow();
                stage.close();
            }
            limpiarCampos();
            //Cerrar Ventana
            Stage stage = (Stage) this.guardarbtn.getScene().getWindow();
            stage.close();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbProveedores.setItems(SinglentonProveedores.getInstance().getLista());
    }
        ////////////
        public void indiceProductos ( int indice){
            this.indice = indice;
            Producto producto = SinglentonProductos.getInstance().getLista().get(indice);
            nombretxt.setText(producto.getNombre());
            txfIdProducto.setText(String.valueOf(producto.getIdP()));
            caducidadtxt.setText(producto.getFechaDeCaducidad());
            cantidadtxt.setText(String.valueOf(producto.getCantidad()));
            preciodeventatxt.setText(String.valueOf(producto.getPrecioDeVenta()));
            preciounitariotxt.setText(String.valueOf(producto.getPrecioUnitario()));
            System.out.println(producto.getImagen());
            imgProducto.setImage(producto.getImagen().getImage());
            cmbProveedores.getSelectionModel().select(producto.getProveedor());
        }

        private void limpiarCampos(){
            nombretxt.clear();
            cantidadtxt.clear();
            caducidadtxt.clear();
            txfIdProducto.clear();
            preciounitariotxt.clear();
            preciodeventatxt.clear();
            this.imgProducto.setImage(null);
            cmbProveedores.getSelectionModel().clearSelection();
        }

        private boolean idDuplicados(int nuevoId){
        ObservableList<Producto> listaProducto = SinglentonProductos.getInstance().getLista();
        return listaProducto.stream().anyMatch(producto -> producto.getIdP() == nuevoId);
        }
        private void mensajeError(String titulo, String mensaje){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }

        private boolean nombresDuplicados(String nombre){
        return SinglentonProductos.getInstance().getLista().stream().anyMatch(producto -> producto.getNombre().equalsIgnoreCase(nombre));
        }
}




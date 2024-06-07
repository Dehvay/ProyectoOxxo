package fes.aragon.proyectooxxo.controller;

import fes.aragon.proyectooxxo.modelo.Producto;
import fes.aragon.proyectooxxo.modelo.SerializableImage;
import fes.aragon.proyectooxxo.modelo.SinglentonProductos;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AgregarProductoController {

    @FXML
    private Button btnAbrirImagen;
    @FXML
    private TextField caducidadtxt;

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
        //Verificar id de Productos
        int nuevoId = Integer.parseInt(txfIdProducto.getText());
        boolean idDuplicado = idDuplicados(nuevoId);
        if (idDuplicado){
            mensajeError("Error: ID duplicado","Este ID ya esta en uso, Elige otro ID.");
        }else {//Guardado de Producto
            Producto producto = new Producto();
            producto.setNombre(nombretxt.getText());
            producto.setIdP(Integer.parseInt(txfIdProducto.getText()));
            producto.setFechaDeCaducidad(caducidadtxt.getText());
            producto.setPrecioDeVenta(Double.parseDouble(preciodeventatxt.getText()));
            producto.setPrecioUnitario(Double.parseDouble(preciounitariotxt.getText()));
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
        }

        }
        ////////////
        public void indiceProductos ( int indice){
            this.indice = indice;
            Producto producto = SinglentonProductos.getInstance().getLista().get(indice);
            nombretxt.setText(producto.getNombre());
            txfIdProducto.setText(String.valueOf(producto.getIdP()));
            caducidadtxt.setText(producto.fechaDeCaducidad);
            preciodeventatxt.setText(String.valueOf(producto.getPrecioDeVenta()));
            preciounitariotxt.setText(String.valueOf(producto.getPrecioUnitario()));
            System.out.println(producto.getImagen());
            imgProducto.setImage(producto.getImagen().getImage());
        }

        private void limpiarCampos(){
            nombretxt.clear();
            caducidadtxt.clear();
            txfIdProducto.clear();
            preciounitariotxt.clear();
            preciodeventatxt.clear();
            this.imgProducto.setImage(null);
        }

        private boolean idDuplicados(int nuevoId){
            ObservableList<Producto> listaProducto = SinglentonProductos.getInstance().getLista();
//            return listaProducto.stream().anyMatch(producto -> producto.getIdP() == nuevoId);
            return false;
        }
        private void mensajeError(String titulo, String mensaje){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }
}




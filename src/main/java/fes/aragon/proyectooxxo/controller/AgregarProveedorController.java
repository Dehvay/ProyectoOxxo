package fes.aragon.proyectooxxo.controller;

import fes.aragon.proyectooxxo.modelo.Proveedor;
import fes.aragon.proyectooxxo.modelo.SerializableImage;
import fes.aragon.proyectooxxo.modelo.SinglentonProveedores;
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
import java.util.Objects;

public class AgregarProveedorController {

    @FXML
    private Button btnImagen;

    @FXML
    private Button btnguardar;
    @FXML
    private TextField correotxt;

    @FXML
    private TextField direcciontxt;

    @FXML
    private TextField nombreproveedortxt;

    @FXML
    private TextField telefonotxt;
    @FXML
    private ImageView imgProveedor;
    private Integer indice;
    private File selectedFile;

    @FXML
    void abrirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("imagen", "*.png", "*.jpeg"));
        this.selectedFile = fileChooser.showOpenDialog(this.btnguardar.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Image imagen = new Image(fileInputStream);
                this.imgProveedor.setImage(imagen);
            } catch (IOException e) {
                mensajeError("Error al abrir la imagen", "No se puedo cargar la imagen.");
            }
        }
    }

    @FXML
    void guardarProveedor(ActionEvent event) {
        String nombreProveedor = nombreproveedortxt.getText();

        //Verificar telefono proveedor
        String nuevoTelefono = (telefonotxt.getText());
        boolean telefonoDuplicado = telefonoDuplicados(nuevoTelefono);
        if (telefonoDuplicado){
            mensajeError("Error: Telefono duplicado","Este Telefono ya esta registrado para un proveedor, Ingresa otro Telefono.");
        }else {//Guardado de Proveedor
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(nombreproveedortxt.getText());
            proveedor.setCorreo(correotxt.getText());
            proveedor.setTelefono(telefonotxt.getText());
            proveedor.setDireccion(direcciontxt.getText());
            if (selectedFile != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    Image imagen = new Image(fileInputStream);
                    SerializableImage imag = new SerializableImage();
                    imag.setImage(imagen);
                    proveedor.setImagen(imag);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (indice == null) {
                SinglentonProveedores.getInstance().getLista().add(proveedor);
            } else {
                Image image = this.imgProveedor.getImage();
                SerializableImage image1 = new SerializableImage();
                image1.setImage(image);
                proveedor.setImagen(image1);
                SinglentonProveedores.getInstance().getLista().set(indice, proveedor);
                Stage stage = (Stage) this.btnguardar.getScene().getWindow();
                stage.close();
            }
            limpiarCampos();
        }
    }
    public void indiceProveedores ( int indice){
        this.indice = indice;
        Proveedor proveedor = SinglentonProveedores.getInstance().getLista().get(indice);
        nombreproveedortxt.setText(proveedor.getNombre());
        telefonotxt.setText(proveedor.getTelefono());
        correotxt.setText(proveedor.getCorreo());
        direcciontxt.setText(proveedor.getDireccion());
        System.out.println(proveedor.getImagen());
        imgProveedor.setImage(proveedor.getImagen().getImage());
    }
    private void limpiarCampos(){
        nombreproveedortxt.clear();
        correotxt.clear();
        direcciontxt.clear();
        telefonotxt.clear();
        this.imgProveedor.setImage(null);
    }

    private boolean telefonoDuplicados(String nuevoTelefono){
        ObservableList<Proveedor> listaProveedor = SinglentonProveedores.getInstance().getLista();
        return listaProveedor.stream().anyMatch(proveedor -> Objects.equals(proveedor.getTelefono(), nuevoTelefono));

    }
    private void mensajeError(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

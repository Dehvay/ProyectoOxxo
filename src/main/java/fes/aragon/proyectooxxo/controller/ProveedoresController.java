package fes.aragon.proyectooxxo.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fes.aragon.proyectooxxo.modelo.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ProveedoresController implements Initializable {

    @FXML
    private TableColumn<Proveedor, String> cmlCorreo;

    @FXML
    private TableColumn<Proveedor, String> cmlDireccion;

    @FXML
    private TableColumn<Proveedor, SerializableImage> cmlImagen;

    @FXML
    private TableColumn<Proveedor, String> cmlNombre;

    @FXML
    private TableColumn<Proveedor, String> cmlOperaciones;

    @FXML
    private TableColumn<Proveedor, String> cmlTelefono;

    @FXML
    private Button idAgregarProveedor;

    @FXML
    private Button idArchivoProveedor;

    @FXML
    private Button idGuardarProveedor;

    @FXML
    private TableView<Proveedor> tblProveedores;
    private ProductosController productosController;


    @FXML
    void eventoAgregarProveedor(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_proveedor.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("PROVEEDORES OXXO");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void eventoArchivoProveedor(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("FES", "*.fes"));
        File selectedFile = fileChooser.showOpenDialog(this.idArchivoProveedor.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                ObjectInputStream entrada = new ObjectInputStream(fo);
                ArrayList<Proveedor> datos = (ArrayList<Proveedor>)entrada.readObject();
                SinglentonProveedores.getInstance().getLista().clear();
                for (Proveedor us : datos) {
                    System.out.println(us.getImagen());
                    SinglentonProveedores.getInstance().getLista().add(us);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void eventoGuardarProveedor(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Proveedores");
        File selectedFile = fileChooser.showSaveDialog(this.idGuardarProveedor.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileOutputStream fo = new FileOutputStream(selectedFile);
                ObjectOutputStream salida = new ObjectOutputStream(fo);
                ArrayList<Proveedor> datos = SinglentonProveedores.getInstance().getConversion();
                salida.writeObject(datos);
                salida.close();
                System.out.println("Los datos se han guardado correctamente en: " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar los datos", e);
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cmlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.cmlDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.cmlCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.cmlTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.cmlImagen.setCellValueFactory(cellData -> {
            String nombreImagen = cellData.getValue().getNombreImagen();
            SerializableImage image = cellData.getValue().getImagen();
            return new SimpleObjectProperty<>(image);
        });
        tblProveedores.setItems(SinglentonProveedores.getInstance().getLista());
        Callback<TableColumn<Proveedor,String>, TableCell<Proveedor,String>>
                celda=(TableColumn<Proveedor,String> parametros)->{
            final TableCell<Proveedor,String> cel=new TableCell<>(){
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    if(b){
                        setGraphic(null);
                        setText(null);
                    }else{
                        FontAwesomeIconView borrarIcono=new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        borrarIcono.setGlyphStyle("-fx-cursor:hand;"+"-glyph-size:28px;"+"-fx-fill:#ff1744");

                        FontAwesomeIconView modificarIcono=new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        modificarIcono.setGlyphStyle("-fx-cursor:hand;"+"-glyph-size:28px;"+"-fx-fill:#ff1744");
                        borrarIcono.setOnMouseClicked((MouseEvent evento)->{
                            int indice=tblProveedores.getSelectionModel().getSelectedIndex();
                            SinglentonProveedores.getInstance().getLista().remove(indice);
                           /* if (indice >= 0) {
                                Proveedor proveedor = tblProveedores.getSelectionModel().getSelectedItem();
                                //Mensaje de Confirmacion
                                List<String> nombresProductos = SinglentonProductos.getInstance().getLista().stream()
                                        .filter(producto -> producto.getProveedor().equals(proveedor))
                                        .map(Producto::getNombre)
                                        .collect(Collectors.toList());
                                String productosAsociados = String.join(", ", nombresProductos);
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmación de eliminación");
                                alert.setHeaderText("¿Estás seguro de querer eliminar a este proveedor y sus productos asociados?");
                                alert.setContentText("Proveedor: " + proveedor.getNombre() + "\nProductos asociados: " + productosAsociados
                                        + "\n\nEsta acción no se puede deshacer.");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK){
                                    // Eliminar productos asociados al proveedor
                                    SinglentonProductos.getInstance().getLista().removeIf(producto -> producto.getProveedor().equals(proveedor));
                                    // Eliminar el proveedor
                                    SinglentonProveedores.getInstance().getLista().remove(indice);
                                    // Actualizar la tabla de productos
                                    if (productosController != null){
                                        productosController.refresTableView();
                                    }
                                }

                            }*/
                        });
                        modificarIcono.setOnMouseClicked((MouseEvent evento)-> modificarUsuario(tblProveedores.getSelectionModel().getSelectedIndex()));
                        HBox hBox=new HBox(modificarIcono,borrarIcono);
                        hBox.setStyle("-fx-alignment:center");
                        HBox.setMargin(modificarIcono, new Insets(2,2,0,3));
                        HBox.setMargin(borrarIcono, new Insets(2,2,0,3));
                        setGraphic(hBox);
                        setText(null);
                    }

                }
            };
            return cel;
        };
        this.cmlOperaciones.setCellFactory(celda);
    }
    private void modificarUsuario(int indice){
        try {
            FXMLLoader modificar = new FXMLLoader(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_proveedor.fxml"));
            Parent parent = modificar.load();
            ((AgregarProveedorController) modificar.getController()).indiceProveedores(indice);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

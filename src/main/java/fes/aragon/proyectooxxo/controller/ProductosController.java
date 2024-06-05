package fes.aragon.proyectooxxo.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import fes.aragon.proyectooxxo.modelo.Producto;
import fes.aragon.proyectooxxo.modelo.Proveedor;
import fes.aragon.proyectooxxo.modelo.SerializableImage;
import fes.aragon.proyectooxxo.modelo.SinglentonProductos;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TableColumn<Producto, String> cmlFechaCaducidad;

    @FXML
    private TableColumn<Producto, Integer> cmlId;

    @FXML
    private TableColumn<Producto, SerializableImage> cmlImagen;

    @FXML
    private TableColumn<Producto, String> cmlNombre;

    @FXML
    private TableColumn<Producto, String> cmlOperaciones;

    @FXML
    private TableColumn<Producto, Double> cmlPrecioUnitario;

    @FXML
    private TableColumn<Producto, Double> cmlPrecioVenta;

    @FXML
    private TableColumn<Producto, Integer> cmlCantidad;

    @FXML
    private TableColumn<Producto, String> cmlProveedor;

    @FXML
    private Button idArchivoProductos;

    @FXML
    private Button idGuardarProductos;
    @FXML
    private TableView<Producto> tblProductos;


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
        FileChooser fileChooser = new FileChooser();
        //      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("FES", "*.fes"));
        File selectedFile = fileChooser.showOpenDialog(this.idArchivoProductos.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                ObjectInputStream entrada = new ObjectInputStream(fo);
                ArrayList<Producto> datos = (ArrayList<Producto>)entrada.readObject();
                SinglentonProductos.getInstance().getLista().clear();
                for (Producto us : datos) {
                    System.out.println(us.getImagen());
                    SinglentonProductos.getInstance().getLista().add(us);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void eventoGuardarProductos(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Guardar Productos");
      File selectedFile = fileChooser.showSaveDialog(this.idGuardarProductos.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileOutputStream fo = new FileOutputStream(selectedFile);
                ObjectOutputStream salida = new ObjectOutputStream(fo);
                ArrayList<Producto> datos = SinglentonProductos.getInstance().getConversion();
                salida.writeObject(datos);
                salida.close();
                System.out.println("Los datos se han guardado correctamente en: " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar los datos", e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cmlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.cmlCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.cmlFechaCaducidad.setCellValueFactory(new PropertyValueFactory<>("fechaDeCaducidad"));
        this.cmlId.setCellValueFactory(new PropertyValueFactory<>("idP"));
        this.cmlPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioDeVenta"));
        this.cmlPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.cmlImagen.setCellValueFactory(cellData -> {
            String nombreImagen = cellData.getValue().getNombreImagen();
            SerializableImage image = cellData.getValue().getImagen();
            return new SimpleObjectProperty<>(image);
        });
        this.cmlProveedor.setCellValueFactory(cellData -> {
            Proveedor proveedor = cellData.getValue().getProveedor();
            if (proveedor != null) {
                return new SimpleStringProperty(proveedor.getNombre());
            } else {
                return new SimpleStringProperty("Sin proveedor");
            }
        });
        tblProductos.setItems(SinglentonProductos.getInstance().getLista());
        Callback<TableColumn<Producto,String>, TableCell<Producto,String>>
                celda=(TableColumn<Producto,String> parametros)->{
            final TableCell<Producto,String> cel=new TableCell<>(){
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
                            int indice=tblProductos.getSelectionModel().getSelectedIndex();
                            SinglentonProductos.getInstance().getLista().remove(indice);
                        });
                        modificarIcono.setOnMouseClicked((MouseEvent evento)->{
                            modificarUsuario(tblProductos.getSelectionModel().getSelectedIndex());
                        });
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
            FXMLLoader modificar = new FXMLLoader(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_producto.fxml"));
            Parent parent = (Parent) modificar.load();
            ((AgregarProductoController) modificar.getController()).indiceProductos(indice);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void refresTableView(){
        Platform.runLater(() -> tblProductos.refresh());
    }
}

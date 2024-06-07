package fes.aragon.proyectooxxo.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fes.aragon.proyectooxxo.modelo.*;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML
    private TableColumn<ItemInventario, Integer> clmCantidad;

    @FXML
    private TableColumn<ItemInventario, String> clmCategoria;

    @FXML
    private TableColumn<Producto, Integer> clmIdProducto;

    @FXML
    private TableColumn<Producto, String> clmNombreProducto;

    @FXML
    private TableColumn<Producto, Double> clmPrecioUnitario;

    @FXML
    private TableColumn<Producto, Double> clmPrecioVenta;

    @FXML
    private TableColumn<Proveedor, String> clmProveedor;

    @FXML
    private TableColumn<ItemInventario, Double> clmValorInventario;
    @FXML
    private TableColumn<ItemInventario, String> clmOperaciones;

    @FXML
    private Button idbtnAbrirInventario;

    @FXML
    private Button idbtnAgregarProdI;

    @FXML
    private Button idbtnGuardarInvent;

    @FXML
    private TableView<ItemInventario> tblInventario;

    @FXML
    void eventoAbrirInventario(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("FES", "*.fes"));
        File selectedFile = fileChooser.showOpenDialog(this.idbtnAbrirInventario.getScene().getWindow());
        if (selectedFile != null) {
            try {
                FileInputStream fo = new FileInputStream(selectedFile);
                ObjectInputStream entrada = new ObjectInputStream(fo);
                ArrayList<ItemInventario> datos = (ArrayList<ItemInventario>)entrada.readObject();
                SinglentonItemInventario.getInstance().getLista().clear();
                for (ItemInventario us : datos) {
                    SinglentonItemInventario.getInstance().getLista().add(us);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void eventoAgregarProdaInventario(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_item.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("INGRESAR A INVENTARIO OXXO");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void eventoGuardarInventario(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Productos");
        File selectedFile = fileChooser.showSaveDialog(this.idbtnGuardarInvent.getScene().getWindow());
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
        this.clmNombreProducto.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));
        this.clmIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        this.clmProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        this.clmCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadIngresada"));
        this.clmPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        this.clmPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        this.clmValorInventario.setCellValueFactory(new PropertyValueFactory<>("valorInventario"));
        this.clmCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tblInventario.setItems(SinglentonItemInventario.getInstance().getLista());

        Callback<TableColumn<ItemInventario,String>, TableCell<ItemInventario,String>>
                celda=(TableColumn<ItemInventario,String> parametros)->{
            final TableCell<ItemInventario, String> cel= new TableCell<>(){
                @Override
                protected void updateItem(String s, boolean b) {
                    super.updateItem(s, b);
                    if(b){
                        setGraphic(null);
                        setText(null);
                    }else{
                        FontAwesomeIconView borrarIcono = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        borrarIcono.setGlyphStyle("-fx-cursor:hand;"+"-glyph-size:28px;"+"-fx-fill:#ff1744");

                        FontAwesomeIconView modificarIcono=new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        modificarIcono.setGlyphStyle("-fx-cursor:hand;"+"-glyph-size:28px;"+"-fx-fill:#ff1744");
                        borrarIcono.setOnMouseClicked((MouseEvent evento)->{
                            int indice=tblInventario.getSelectionModel().getSelectedIndex();
                            SinglentonItemInventario.getInstance().getLista().remove(indice);
                        });
                        modificarIcono.setOnMouseClicked((MouseEvent evento)-> modificarUsuario(tblInventario.getSelectionModel().getSelectedIndex()));
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
        this.clmOperaciones.setCellFactory(celda);
    }

    private void modificarUsuario(int indice){
        try {
            FXMLLoader modificar = new FXMLLoader(getClass().getResource("/fes/aragon/proyectooxxo/xml/registro_de_item.fxml"));
            Parent parent = modificar.load();
            ((AgregarItemInventarioController) modificar.getController()).indiceItems(indice);
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

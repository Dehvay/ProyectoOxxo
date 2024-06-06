package fes.aragon.proyectooxxo.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SinglentonProductos {
    private static SinglentonProductos singlentonProductos;
    private final ObservableList<Producto> lista;
    private SinglentonProductos(){ lista = FXCollections.observableArrayList();}
    public static SinglentonProductos getInstance(){
        if (singlentonProductos == null){
            singlentonProductos = new SinglentonProductos();
        }
        return singlentonProductos;
    }
    public ObservableList<Producto> getLista(){return lista;}
    public ArrayList<Producto> getConversion(){return new ArrayList<>(lista);}

}

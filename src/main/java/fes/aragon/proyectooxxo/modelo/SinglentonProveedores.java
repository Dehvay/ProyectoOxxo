package fes.aragon.proyectooxxo.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SinglentonProveedores {
    private static SinglentonProveedores singlentonProveedores;
    private ObservableList<Proveedor> lista;
    private SinglentonProveedores(){ lista = FXCollections.observableArrayList();}
    public static SinglentonProveedores getInstance(){
        if (singlentonProveedores == null){
            singlentonProveedores = new SinglentonProveedores();
        }
        return singlentonProveedores;
    }
    public ObservableList<Proveedor> getLista(){return lista;}
    public ArrayList<Proveedor> getConversion(){return new ArrayList<>(lista);}

}

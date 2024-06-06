package fes.aragon.proyectooxxo.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SinglentonItemInventario {

    private static SinglentonItemInventario singlentonItemInventario;
    private final ObservableList<ItemInventario> lista;

    private SinglentonItemInventario() {
        lista = FXCollections.observableArrayList();
    }

    public static SinglentonItemInventario getInstance() {
        if (singlentonItemInventario == null) {
            singlentonItemInventario = new SinglentonItemInventario();
        }
        return singlentonItemInventario;
    }

    public ObservableList<ItemInventario> getLista(){return lista;}

    public ArrayList<ItemInventario> getConversion(){return new ArrayList<>(lista);}
}

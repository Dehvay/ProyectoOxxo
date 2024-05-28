package fes.aragon.proyectooxxo.modelo;

import java.util.ArrayList;

public class ItemInventario {

    private Proveedor proveedorI;
    private ArrayList<Producto> productoI;

    public ItemInventario(Proveedor proveedorI, ArrayList<Producto> productoI) {
        this.proveedorI = proveedorI;
        this.productoI = productoI;
    }

    public Proveedor getProveedorI() {
        return proveedorI;
    }

    public void setProveedorI(Proveedor proveedorI) {
        this.proveedorI = proveedorI;
    }

    public ArrayList<Producto> getProductoI() {
        return productoI;
    }

    public void setProductoI(ArrayList<Producto> productoI) {
        this.productoI = productoI;
    }
}

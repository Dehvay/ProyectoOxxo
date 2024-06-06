package fes.aragon.proyectooxxo.modelo;

public class ItemInventario {

    private Proveedor proveedorI;
    private Producto productoI;
    private int cantidadIngresada;
    private String categoria;
    private double valorInventario;

    public ItemInventario() {

    }

    public Proveedor getProveedorI() {
        return proveedorI;
    }

    public void setProveedorI(Proveedor proveedorI) {
        this.proveedorI = proveedorI;
    }

    public Producto getProductoI() {
        return productoI;
    }

    public void setProductoI(Producto productoI) {
        this.productoI = productoI;
    }

    public int getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(int cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorInventario() {
        return valorInventario = getPrecioVenta() * cantidadIngresada;
    }

    public void setValorInventario(double valorInventario) {
        this.valorInventario = valorInventario;
    }

    public String getProductoNombre(){ return productoI != null ? productoI.getNombre() : "Producto no asignado";}
    public Integer getIdProducto(){return productoI != null ? productoI.getIdP() : '0';}
    public String getProveedor(){return proveedorI != null ? proveedorI.getNombre() : "Proveedor no asignado";}
    public Double getPrecioUnitario(){return productoI != null ? productoI.getPrecioUnitario() : '0';}
    public Double getPrecioVenta(){return productoI != null ? productoI.getPrecioDeVenta() : '0';}

}

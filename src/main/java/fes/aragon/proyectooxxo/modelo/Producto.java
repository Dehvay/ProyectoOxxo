package fes.aragon.proyectooxxo.modelo;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Producto implements Serializable{
    private String nombre;
    private String fechaDeCaducidad;
    private int cantidad;
    private double precioUnitario;
    private double precioDeVenta;
    private SerializableImage imagen;
    private int idP;
    private String nombreImagen;
    private Proveedor proveedor;
    public Producto(){

    }

    public Producto(String nombre, String fechaDeCaducidad, int cantidad, double precioUnitario, double precioDeVenta, SerializableImage imagen, int idP, String nombreImagen, Proveedor proveedor) {
        this.nombre = nombre;
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioDeVenta = precioDeVenta;
        this.imagen = imagen;
        this.idP = idP;
        this.nombreImagen = nombreImagen;
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    public void setFechaDeCaducidad(String fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public SerializableImage getImagen() {
        return imagen;
    }

    public void setImagen(SerializableImage imagen) {
        this.imagen = imagen;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

}

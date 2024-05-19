package fes.aragon.proyectooxxo.modelo;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Producto implements Serializable{
    public String nombre;
    public String fechaDeCaducidad;
    public int cantidad;
    public double precioUnitario;
    public double precioDeVenta;
    public String categoria;
    private SerializableImage imagen;

    public Producto(){

    }

    public Producto(String nombre, String fechaDeCaducidad, int cantidad, double precioUnitario, double precioDeVenta, String categoria) {
        this.nombre = nombre;
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioDeVenta = precioDeVenta;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public SerializableImage getImagen() {
        return imagen;
    }

    public void setImagen(SerializableImage imagen) {
        this.imagen = imagen;
    }
}

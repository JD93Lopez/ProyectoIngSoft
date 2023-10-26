package clases;

import java.io.Serializable;

public class ProductoVenta implements Serializable {

    int idProducto;
    String nombreProducto;
    double cantidadVentas;
    double totalVentas;
    double ingreso;

    public ProductoVenta(int idProducto, double cantidadVentas) {
        this.idProducto = idProducto;
        this.cantidadVentas = cantidadVentas;
    }

    public ProductoVenta(int idProducto,double cantidadVentas,double totalVentas,double ingreso){
        this.idProducto = idProducto;
        this.cantidadVentas = cantidadVentas;
        this.totalVentas = totalVentas;
        this.ingreso = ingreso;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(double cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }
}

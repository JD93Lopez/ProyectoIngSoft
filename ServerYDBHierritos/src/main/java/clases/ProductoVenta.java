package clases;

import java.io.Serializable;

public class ProductoVenta implements Serializable {

    int idProducto;
    String nombreProducto;
    double totalVentas;

    public ProductoVenta(int idProducto, double totalVentas) {
        this.idProducto = idProducto;
        this.totalVentas = totalVentas;
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

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }
}

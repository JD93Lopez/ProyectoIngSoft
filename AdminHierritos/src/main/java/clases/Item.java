package clases;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Item implements Serializable {
    private final IntegerProperty idProducto;

    private final StringProperty nombreProducto;

    private final IntegerProperty cantidadCompra;

    private final IntegerProperty cantidadVenta;

    public Item(int idProducto, String nombreProducto, int cantidadCompra, int cantidadVenta) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
        this.cantidadCompra = new SimpleIntegerProperty(cantidadCompra);
        this.cantidadVenta = new SimpleIntegerProperty(cantidadVenta);
    }

    public int getIdProducto() {
        return idProducto.get();
    }

    public IntegerProperty idProductoProperty() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto.set(idProducto);
    }

    public String getNombreProducto() {
        return nombreProducto.get();
    }

    public StringProperty nombreProductoProperty() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto.set(nombreProducto);
    }

    public int getCantidadCompra() {
        return cantidadCompra.get();
    }

    public IntegerProperty cantidadCompraProperty() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra.set(cantidadCompra);
    }

    public int getCantidadVenta() {
        return cantidadVenta.get();
    }

    public IntegerProperty cantidadVentaProperty() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta.set(cantidadVenta);
    }
}

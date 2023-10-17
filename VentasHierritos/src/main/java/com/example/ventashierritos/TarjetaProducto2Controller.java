package com.example.ventashierritos;

import clases.Producto;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TarjetaProducto2Controller {
    static Scene scene;
    TarjetaProducto2Controller controller;
    @FXML
    HBox tarjeta;
    @FXML
    Label labelNomProducto;
    @FXML
    Label labelIDProducto;
    @FXML
    Label labelCantidad;
    @FXML
    protected void clickBotonX(){
        BuscarCliente2Controller.tarjetasProductosSeleccionados.remove(controller);
        BuscarCliente2Controller.controller.gridPane2.getChildren().remove(controller.tarjeta);
        BuscarCliente2Controller.controller.total-=((
                producto.getPrecioVenta()-(producto.getPrecioVenta()*producto.getpDescuento())
        )*producto.getExistencias());
        BuscarCliente2Controller.controller.setLabelTotal("Total: "+BuscarCliente2Controller.controller.total);
    }

    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setLabelNomProducto(String nomProducto) {
        labelNomProducto.setText(nomProducto);
    }

    public void setLabelIDProducto(String idProducto) {
        labelIDProducto.setText(idProducto);
    }

    public void setLabelCantidad(String cantidad) {
        labelCantidad.setText(cantidad);
    }
}

package com.example.almacenhierritos;

import clases.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TarjetaProveedorController {

    @FXML
    private VBox vBoxTarjeta;

    @FXML
    private Label labelNombre;
    @FXML
    Label labelNIT;
    @FXML
    Label labelID;

    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public VBox getTarjeta(){
        return vBoxTarjeta;
    }

    public void setLabelNombre(String nombreProducto) {
        this.labelNombre.setText(nombreProducto);
    }
    public void setLabelNIT(String nit) {
        this.labelNIT.setText("Nit: "+nit);
    }
    public void setLabelID(String id) {
        this.labelID.setText("Id: "+id);
    }

}

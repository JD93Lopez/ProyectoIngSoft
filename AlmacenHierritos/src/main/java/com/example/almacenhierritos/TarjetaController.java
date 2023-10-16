package com.example.almacenhierritos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TarjetaController {
    @FXML
    ImageView imageWarning;
    @FXML
    private VBox vBoxTarjeta;

    @FXML
    private Label labelNombreProducto;

    @FXML
    private Label labelDescProducto;

    @FXML
    private TextField textFieldCantidad;

    public TarjetaController() {
    }

    public VBox getTarjeta(){
        return vBoxTarjeta;
    }

    public void setLabelNombreProducto(String NombreProducto) {
        this.labelNombreProducto.setText(NombreProducto);
    }

    public void setLabelDescProducto(String DescProducto) {
        this.labelDescProducto.setText(DescProducto);
    }

    public void setTextFieldCantidad(String Cantidad) {
        this.textFieldCantidad.setText(Cantidad);
    }

    public String getTextFieldCantidad() {
        return this.textFieldCantidad.getText();
    }
}

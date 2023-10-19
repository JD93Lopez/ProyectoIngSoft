package com.example.almacenhierritos;

import clases.Cliente;
import clases.Producto;
import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

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
    @FXML
    private Label labelPrecio;
    @FXML
    public void clickChulito() {
        try {
            Client.client.actualizarExistencias(textFieldCantidad.getText(),producto.getIdProducto());
            cuadroExistenciasModificadas();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void cuadroExistenciasModificadas() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Existencias Modificadas");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Existencias del producto modificadas correctamente.");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }

    public TarjetaController() {
    }

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

    public void setLabelNombreProducto(String nombreProducto) {
        this.labelNombreProducto.setText(nombreProducto);
    }

    public void setLabelDescProducto(String descProducto) {
        this.labelDescProducto.setText(descProducto);
    }

    public void setTextFieldCantidad(String cantidad) {
        this.textFieldCantidad.setText(cantidad);
    }

    public void setLabelPrecio(String precio) {
        this.labelPrecio.setText(precio);
    }

    public String getTextFieldCantidad() {
        return this.textFieldCantidad.getText();
    }
}

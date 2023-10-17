package com.example.ventashierritos;

import clases.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TarjetaController {
    TarjetaController controller;

    @FXML
    ImageView imageWarning;
    @FXML
    private VBox vBoxTarjeta;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label labelNombreProducto;

    @FXML
    private Label labelDescProducto;

    @FXML
    private TextField textFieldCantidad;

    private Producto producto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TarjetaController() {
    }

    @FXML
    protected void clickBotonChulito() {
        try{
            //TODO verificar cantidad vendida menor que existencias
            producto.setExistencias(Double.valueOf(textFieldCantidad.getText()));
            BuscarCliente2Controller.controller.insertarTarjetaPequena(producto);
            BuscarCliente2Controller.controller.total+=((
                    producto.getPrecioVenta()-(producto.getPrecioVenta()*producto.getpDescuento())
            )*producto.getExistencias());
            BuscarCliente2Controller.controller.setLabelTotal("Total: "+BuscarCliente2Controller.controller.total);
        }catch (Exception e){
            cuadroValorNoNumerico();
        }
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
        this.labelPrecio.setText("$"+precio);
    }

    public String getTextFieldCantidad() {
        return this.textFieldCantidad.getText();
    }

    private void cuadroValorNoNumerico() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Valor No Numérico");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Por favor asegúrese de que colocó un valor numérico en el campo de cantidad.");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
}

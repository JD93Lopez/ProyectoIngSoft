package com.example.ventashierritos;

import clases.EmpresaProveedora;
import client.Client;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

public class CotizacionController {
    static Scene scene;
    static CotizacionController controller;
    @FXML
    TextField textFieldId;
    public void clickBotonBuscar() {
        //TODO
    }
    public void clickBotonCancelar() {
        BuscarClienteController.controller.limpiarCampos();
        textFieldId.setText("");
        BuscarCliente2Controller.controller.reiniciarListasYGrids();
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonPagarCot() {
        boolean bool = false;
        try {
            bool = Client.client.pagarCotizacion(textFieldId.getText(),cuadroFormaDePago());
        } catch (RemoteException e) {
            bool = false;
            throw new RuntimeException(e);
        }
        if (bool){
            BuscarClienteController.controller.limpiarCampos();
            FacturaController.controller.setLabelIdFactura("ID: "+textFieldId.getText());
            textFieldId.setText("");
            BuscarCliente2Controller.controller.reiniciarListasYGrids();
            Main.mainStage.setScene(FacturaController.scene);
        }else{
            cuadroErrorCotizacion();
        }
    }
    public void setTextFieldId(String id){
        textFieldId.setText(id);
    }
    private EmpresaProveedora.FormaDePago cuadroFormaDePago() {
        // Crear un cuadro de diálogo emergente (Alert) de tipo Confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Método de Pago");
        alert.setHeaderText("Selecciona un método de pago:");

        // Crear un ChoiceBox para seleccionar el método de pago
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Efectivo", "Tarjeta", "Transferencia");
        choiceBox.setValue("Efectivo"); // Opción predeterminada

        // Agregar el ChoiceBox al contenido del cuadro de diálogo
        VBox vbox = new VBox(choiceBox);
        alert.getDialogPane().setContent(vbox);

        // Mostrar el cuadro de diálogo y esperar a que el usuario seleccione una opción
        alert.showAndWait();

        // Obtener la opción seleccionada y retornarla como un String
        String metodoSeleccionado = choiceBox.getValue();

        // Llamar al método que procesa la opción seleccionada (puedes hacer lo que necesites aquí)
        return  Enum.valueOf(EmpresaProveedora.FormaDePago.class,metodoSeleccionado.toUpperCase());
    }

    private void cuadroErrorCotizacion() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Pagando la Cotización");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Hubo un error pagando la cotización, verifique el id e inténtelo de nuevo.");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
}

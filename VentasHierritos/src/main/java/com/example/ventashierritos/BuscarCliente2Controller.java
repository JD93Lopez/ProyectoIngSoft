package com.example.ventashierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class BuscarCliente2Controller {
    static Scene scene;
    @FXML
    TextField textfieldBuscar;
    public void clickBotonBuscar() {
    }
    public void clickRegresar() {
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonAceptar() {
        Main.mainStage.setScene(FacturaController.scene);
    }
    public void clickBotonCancelar() {
        BuscarClienteController.controller.limpiarCampos();
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonHacerCot() {
        Main.mainStage.setScene(CotizacionController.scene);
    }
}

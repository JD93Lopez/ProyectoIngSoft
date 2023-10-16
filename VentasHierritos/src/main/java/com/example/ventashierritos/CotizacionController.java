package com.example.ventashierritos;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class CotizacionController {
    static Scene scene;
    @FXML
    TextField textFieldId;
    public void clickBotonBuscar() {

    }
    public void clickBotonCancelar() {
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
    public void clickBotonPagarCot() {
        Main.mainStage.setScene(FacturaController.scene);
    }
}

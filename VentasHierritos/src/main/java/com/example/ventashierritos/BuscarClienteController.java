package com.example.ventashierritos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BuscarClienteController {

    static Scene scene;

    @FXML
    TextArea textArea;
    @FXML
    TextField textfieldBuscar;

    public void clickBotonBuscar( ) {
    }

    public void clickBotonSiguiente() {
    }

    public void clickBotonAgCliente( ) {
        Main.mainStage.setScene(AgregarClienteController.scene);
    }

    public void clickBotonPagarCot() {
    }
}

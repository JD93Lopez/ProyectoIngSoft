package com.example.ventashierritos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.time.temporal.Temporal;

public class AgregarClienteController {

    static Scene scene;
    @FXML
    TextField textfieldBuscar;
    @FXML
    TextField textfieldNombres;
    @FXML
    TextField textfieldNumTel;
    @FXML
    SplitMenuButton menuTipoDocumento;
    @FXML
    TextField textfieldDireccion;
    @FXML
    TextField textfieldCorreo;
    @FXML
    SplitMenuButton menuTipoPersona;
    @FXML
    SplitMenuButton menuIVA;

    public void clickBotonBuscar() {
    }
    public void clickBotonAceptar() {
        //TODO guardar cliente
        Main.mainStage.setScene(BuscarClienteController.scene);
    }

    public void clickBotonCancelar() {
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
}

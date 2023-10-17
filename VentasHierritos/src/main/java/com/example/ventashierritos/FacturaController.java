package com.example.ventashierritos;

import javafx.scene.Scene;

public class FacturaController {
    static Scene scene;
    public void clickBotonSalir(){
        BuscarClienteController.controller.limpiarCampos();
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
}

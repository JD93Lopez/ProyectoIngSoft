package com.example.ventashierritos;

import javafx.scene.Scene;

public class FacturaController {
    static Scene scene;
    public void clickBotonSalir(){
        Main.mainStage.setScene(BuscarClienteController.scene);
    }
}

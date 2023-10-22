package com.example.almacenhierritos;

import clases.EmpresaProveedora;
import clases.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TarjetaProveedorController {

    @FXML
    private VBox vBoxTarjeta;

    @FXML
    private Label labelNombre;
    @FXML
    Label labelNIT;
    @FXML
    Label labelID;
    @FXML
    public void clickBotonChulito(){
        AgregarProductoController.empresaActual = getEmpresaProveedora();
        Main.mainStage.setScene(AgregarProductoController.scene);
    }

    private EmpresaProveedora empresaProveedora;

    public EmpresaProveedora getEmpresaProveedora() {
        return empresaProveedora;
    }

    public void setEmpresaProveedora(EmpresaProveedora empresaProveedora) {
        this.empresaProveedora = empresaProveedora;
    }

    public VBox getTarjeta(){
        return vBoxTarjeta;
    }

    public void setLabelNombre(String nombreProducto) {
        this.labelNombre.setText(nombreProducto);
    }
    public void setLabelNIT(String nit) {
        this.labelNIT.setText("Nit: "+nit);
    }
    public void setLabelID(String id) {
        this.labelID.setText("Id: "+id);
    }

}

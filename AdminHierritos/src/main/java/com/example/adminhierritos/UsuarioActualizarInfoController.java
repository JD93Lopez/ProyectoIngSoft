package com.example.adminhierritos;

import Client.Client;
import clases.Persona;
import clases.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.rmi.RemoteException;

public class UsuarioActualizarInfoController {
    static Scene scene;
    static UsuarioActualizarInfoController controller;
    static Usuario usuarioActual=null;
    @FXML
    TextField textfieldBuscar;

    @FXML
    TextField textfieldNombreUsuario;

    @FXML
    TextField textfieldNombresTrabajador;

    @FXML
    ComboBox comboBoxTipoDoc;

    @FXML
    TextField textfieldNumDoc;

    @FXML
    TextField textfieldContrasena;

    @FXML
    TextField textfieldNumTel;

    @FXML
    ComboBox comboBoxTipoUsuario;
    @FXML
    VBox vBox;

    public void ClickBotonBuscar() throws RemoteException {
        Usuario usuario;

        usuario = Client.client.buscarUsuario(textfieldBuscar.getText());
        if(usuario.getId()!=null) {
            usuarioActual = usuario;
            llenarEspacios(usuarioActual);
        }else if(textfieldBuscar.getText().equals("")){
            cuadroRellenarCampoBusqueda();

        }else{
            cuadroUsuarioNoEncontrado();
            System.out.println("UsuarioActualizarInfoController.ClickBotonBuscar");
            usuarioActual=null;
        }
    }

    public void clickBotonGuardarCambios() throws RemoteException {
        String nombreUsuario = textfieldNombreUsuario.getText();
        String telefono = textfieldNumTel.getText();
        String nombres = textfieldNombresTrabajador.getText();
        String contrasena = textfieldContrasena.getText();
        String numDoc = textfieldNumDoc.getText();
        Persona.TipoDocumento tipDoc = (Persona.TipoDocumento) comboBoxTipoDoc.getValue();
        Usuario.TipoUsuario tipoUsuario = (Usuario.TipoUsuario) comboBoxTipoUsuario.getValue();

        if ((tipDoc !=null) && (tipoUsuario != null) ){

            Usuario usuarioTemp = new Usuario(
                    tipoUsuario, nombreUsuario, contrasena, nombres, tipDoc, numDoc
            );
            usuarioTemp.setTelefono(telefono);

            if(usuarioActual==null){
                if(Client.client.crearUsuario(usuarioTemp)) {
                    cuadroExitoCrear();
                    Main.mainStage.setScene(MenuController.scene);
                }
            }else{
                usuarioTemp.setId(usuarioActual.getId());
                if(Client.client.actualizarUsuario(usuarioTemp)) {
                    cuadroExitoActualizar();
                    Main.mainStage.setScene(MenuController.scene);
                }
            }

        }else {
            cuadroRellenarCampos();
        }
    }

    public void clickBotonCrear( ) throws RemoteException {
        String nombreUsuario = textfieldNombreUsuario.getText();
        String telefono = textfieldNumTel.getText();
        String nombres = textfieldNombresTrabajador.getText();
        String contrasena;
        if(textfieldContrasena.getText().equals("")){
            contrasena = null;
        }else{
            contrasena = textfieldContrasena.getText();
        }
        String numDoc = textfieldNumDoc.getText();
        Persona.TipoDocumento tipDoc = (Persona.TipoDocumento) comboBoxTipoDoc.getValue();
        Usuario.TipoUsuario tipoUsuario = (Usuario.TipoUsuario) comboBoxTipoUsuario.getValue();

        if ((tipDoc !=null) && (tipoUsuario != null) ){

            Usuario usuarioTemp = new Usuario(
                    tipoUsuario, nombreUsuario, contrasena, nombres, tipDoc, numDoc);
            usuarioTemp.setTelefono(telefono);

            if(usuarioActual==null){
                if(usuarioTemp.getContrasena()==null){usuarioTemp.setContrasena("");}
                if(Client.client.crearUsuario(usuarioTemp)) {
                    cuadroExitoCrear();
                }
            }else{
                if(Client.client.actualizarUsuario(usuarioTemp)) {
                    cuadroExitoActualizar();
                }
            }

        }else {
            cuadroRellenarCampos();
        }
    }

    public void desplegables() {
        desplegableTipoUsuario();
        desplegableTipoDocumento();
    }

    public void llenarEspacios(Usuario usuarioActual){
        textfieldNombreUsuario.setText(usuarioActual.getNombreUsuario());
        textfieldNumTel.setText(usuarioActual.getTelefono());
        textfieldNombresTrabajador.setText(usuarioActual.getNombres());
        textfieldContrasena.setText(usuarioActual.getContrasena());
        textfieldNumDoc.setText(usuarioActual.getNumDocumento());
        comboBoxTipoDoc.getSelectionModel().select(usuarioActual.getTipoDocumento());
        comboBoxTipoUsuario.getSelectionModel().select(usuarioActual.getTipoUsuario());
    }

    private void cuadroRellenarCampoBusqueda() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rellenar Campos");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Rellene la barra de búsqueda con el id \n del Usuario");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
    private void cuadroRellenarCampos() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rellenar Campos");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("Llene todos los campos por favor");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);
        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
    private void cuadroUsuarioNoEncontrado() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario No Encontrado");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\n Usuario no encontrado. Por favor, agréguelo completando los campos requeridos y haciendo clic en el botón \"Crear\"");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }

    private void cuadroExitoCrear() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Crear Usuario");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\n El usuario ha sido creado con éxito ");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }

    private void cuadroExitoActualizar() {
        // Crear un cuadro de diálogo de tipo INFORMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Actualizar Usuario");
        alert.setHeaderText(null); // Opcional, puedes configurar un encabezado si lo deseas
        alert.setContentText("\n El usuario ha sido actualizado con éxito ");

        // Agregar un botón "Ok"
        ButtonType okButton = new ButtonType("Ok");
        alert.getButtonTypes().setAll(okButton);

        // Mostrar el cuadro de diálogo y esperar a que el usuario lo cierre
        alert.showAndWait();
    }
    public void desplegableTipoDocumento(){
        for (Persona.TipoDocumento tipoDoumento: Persona.TipoDocumento.values()) {
            comboBoxTipoDoc.getItems().add(tipoDoumento);

        }
    }
    public void desplegableTipoUsuario(){
        for (Usuario.TipoUsuario tipoUsuario : Usuario.TipoUsuario.values()) {
            comboBoxTipoUsuario.getItems().add(tipoUsuario);
        }
    }
}

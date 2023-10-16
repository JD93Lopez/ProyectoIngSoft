package server;

import Database.Consulta;
import clases.Cliente;
import clases.Producto;
import clases.Usuario;
import interfaces.RMIVentas;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServiceVentas extends UnicastRemoteObject implements RMIVentas {

    private boolean sesionIniciada = false;
    protected ServiceVentas() throws RemoteException {

    }

    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        Consulta consulta = new Consulta();
        String contrasenaDB = consulta.obtenerContraseñaPorNombre(usuario);
        if(contrasenaDB!=null&&contrasena!=null){
            if(contrasenaDB.equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List ListaProductosInventario() throws RemoteException {
        return Consulta.listaProductosStock();
    }

    @Override
    public Cliente buscarCliente(String telefono) throws RemoteException {
        return Consulta.obtenerClientePorTelefono(telefono);
    }

    @Override
    public Producto buscarProducto(String codigo) throws RemoteException {
        Producto productoPrueba = new Producto("Codigo2","nombreTornillo",  "descripcionLargo",  200,  3,  0.19,  100);
        return productoPrueba;
    }
}

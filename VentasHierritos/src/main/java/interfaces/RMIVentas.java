package interfaces;

import clases.Cliente;
import clases.Producto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIVentas extends Remote {
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;
    public List ListaProductosInventario () throws RemoteException;
    public Cliente buscarCliente (String telefono) throws RemoteException;

    public Producto buscarProducto (String codigo) throws RemoteException;

}

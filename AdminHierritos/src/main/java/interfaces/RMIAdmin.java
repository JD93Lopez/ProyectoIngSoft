package interfaces;

import clases.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface RMIAdmin extends Remote {
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;
    public String informeBalanceMensual() throws RemoteException;
    public String informeDevoluciones() throws RemoteException;
    public Cliente buscarCliente (String telefono) throws RemoteException;
    public Usuario buscarUsuario (String telefono) throws RemoteException;
    public  boolean actualizarCliente (Cliente cliente) throws RemoteException;
    public  boolean actualizarUsuario (Usuario usuario) throws RemoteException;
    public  boolean crearUsuario (Usuario usuario) throws RemoteException;
    public Usuario obtenerUsuario(String nUser, String pass) throws RemoteException;
    public LinkedList<ProductoVenta> informeVentas (String fecha) throws RemoteException;
    public Vendedor informeVendedorMes () throws RemoteException;
}
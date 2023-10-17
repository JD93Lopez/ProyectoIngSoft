package interfaces;

import clases.Cliente;
import clases.FacturaVenta;
import clases.Producto;
import clases.Usuario;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIVentas extends Remote {
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;
    public List listaProductosInventario() throws RemoteException;
    public Cliente buscarCliente (String telefono) throws RemoteException;

    public Producto buscarProducto (String codigo) throws RemoteException;

    public int agregarCliente(Cliente cliente) throws RemoteException;

    public Usuario obtenerVendedor(String usuario, String contrasena) throws RemoteException;
    public int enviarFactura(FacturaVenta facturaVenta) throws RemoteException;
}

package interfaces;

import clases.EmpresaProveedora;
import clases.FacturaCompra;
import clases.Producto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIAlmacen extends Remote {
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;

    public EmpresaProveedora buscarProveedor(String nitONombre) throws RemoteException;

    public List ListaProductosInventario () throws RemoteException;

    public List<EmpresaProveedora> ListaEmpresasProveedoras() throws RemoteException;

    public void enviarProductoInsertar(Producto producto, int id) throws RemoteException;

    public void actualizarExistencias(String text, int idProducto) throws RemoteException;

    public void enviarFacturaDeCompra(FacturaCompra facturaCompra) throws RemoteException;
}

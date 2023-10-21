package server;

import Database.Insercion;
import Database.Update;
import clases.EmpresaProveedora;
import clases.FacturaCompra;
import clases.Producto;
import clases.Usuario;
import interfaces.RMIAlmacen;
import Database.Consulta;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServiceAlmacen extends UnicastRemoteObject implements RMIAlmacen {

    private boolean sesionIniciada = false;

    public ServiceAlmacen() throws RemoteException {
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

/*    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        if(user.getNombreUsuario().equals(usuario)&&user.getContrasena().equals(contrasena)&&user.getTipoUsuario().equals(Usuario.TipoUsuario.BODEGUERO)){
            return true;
        }
        return false;
    }*/

    @Override
    public EmpresaProveedora buscarProveedor(String nit) throws RemoteException {
        return Consulta.obtenerEmpresaProveedoraPorNit(nit);
    }

    @Override
    public List ListaProductosInventario() throws RemoteException {
        return Consulta.listaProductosStock();
    }

    @Override
    public List<EmpresaProveedora> ListaEmpresasProveedoras() throws RemoteException {
        return Consulta.listaEmpresasProveedoras();
    }

    @Override
    public void enviarProductoInsertar(Producto producto, int id) throws RemoteException {
        Insercion.productos(
                ""/*+producto.getCodigo()*/,
                ""+producto.getNombre(),
                ""+producto.getDescripcion(),
                "0"/*+producto.getExistencias()*/,
                ""+producto.getpDescuento(),
                ""+producto.getpIva(),
                ""+producto.getPrecioCompra(),
                ""+producto.getPrecioVenta(),
                ""+producto.getCantidadMinima(),
                ""+producto.getCantidadMaxima(),
                id
        );
    }

    @Override
    public void actualizarExistencias(String text, int idProducto) throws RemoteException {
        Update.setExistencias(text,idProducto);
    }

    @Override
    public void enviarFacturaDeCompra(FacturaCompra facturaCompra) throws RemoteException {
        //TODO
        Insercion.facturasDeCompra(
                ""+facturaCompra.getNombreVendedor(),
                ""+facturaCompra.getFormaDePago(),
                "",
                ""+facturaCompra.getTotal()
        );
    }

    @Override
    public boolean crearEmpresaProveedora(EmpresaProveedora empresaProveedora) throws RemoteException {
        boolean ack = false;
        try {
            if(empresaProveedora.getId()!=0){
                Update.actualizarEmpresaProveedora(empresaProveedora);
            }else {
                Insercion.empresas_proveedoras(
                        ""+empresaProveedora.getNombre(),
                        ""+empresaProveedora.getNit(),
                        ""+empresaProveedora.getBanco(),
                        ""+empresaProveedora.getCuentaBancaria(),
                        empresaProveedora.getpDescuento()
                );
            }
            ack = true;
        }catch (Exception e){
            ack = false;
            e.printStackTrace();
        }
        return ack;
    }

}

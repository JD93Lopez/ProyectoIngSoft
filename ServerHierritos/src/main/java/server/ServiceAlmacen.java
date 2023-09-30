package server;

import clases.EmpresaProveedora;
import clases.Usuario;
import interfaces.RMIAlmacen;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceAlmacen extends UnicastRemoteObject implements RMIAlmacen {

    private boolean sesionIniciada = false;
    private Usuario user=new Usuario(Usuario.TipoUsuario.BODEGUERO,"paola","1234");//TEMP

    public ServiceAlmacen() throws RemoteException {
    }

    @Override
    public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
        if(user.getNombreUsuario().equals(usuario)&&user.getContrasena().equals(contrasena)&&user.getTipoUsuario().equals(Usuario.TipoUsuario.BODEGUERO)){
            return true;
        }
        return false;
    }

    @Override
    public EmpresaProveedora buscarProveedor(String nit) throws RemoteException {
        return new EmpresaProveedora();
    }

}

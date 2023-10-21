package server;

import Database.Consulta;
import Database.Insercion;
import Database.Update;
import clases.*;
import interfaces.RMIAdmin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ServiceAdmin extends UnicastRemoteObject implements RMIAdmin {
    private boolean sesionIniciada = false;

    protected ServiceAdmin() throws RemoteException {

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
    public String informeBalanceMensual() throws RemoteException {
        String test = "Informe Balance Mensual";

        return test;
    }

    @Override
    public String informeDevoluciones() throws RemoteException {
        String test = "Informe Devoluciones";

        return test;
    }

    @Override
    public Cliente buscarCliente(String telefono) throws RemoteException {

        Cliente cliente = null;
        try {
            cliente = Consulta.obtenerClientePorCedula(telefono);
            if (cliente.getId() == null) {
                cliente = Consulta.obtenerClientePorTelefono(telefono);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public Usuario buscarUsuario(String telefono) throws RemoteException {
        Usuario usuario = null;
        try {
            usuario = Consulta.obtenerUsuarioPorNombre(telefono,"");
            if(usuario==null) {
                usuario = Consulta.obtenerUsuarioPorId(telefono);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) throws RemoteException {
        if(cliente.getId()!=null){
            Update.actualizarCliente(cliente);
        }else{
            Insercion.nuevoCliente(
                    ""+cliente.getNombres(),
                    ""+cliente.getTelefono(),
                    ""+cliente.getTipoDocumento(),
                    ""+cliente.getNumDocumento(),
                    ""+cliente.getDireccion(),
                    ""+cliente.getCorreo(),
                    ""+cliente.getTipoPersona(),
                    ""+cliente.getResponsableDeIva(),
                    "0"
            );
        }
        return true;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws RemoteException {
        boolean ack=false;
        try {
            if(usuario.getContrasena().equals("")){
                Update.actualizarUsuarioSinContrasena(usuario);
            }else {
                Update.actualizarUsuario(usuario);
            }
            ack=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ack;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) throws RemoteException {
        boolean ack=false;
        try {
            Insercion.nuevoUsuario(
                    ""+usuario.getNombreUsuario(),
                    ""+usuario.getTelefono(),
                    ""+usuario.getTipoDocumento(),
                    ""+usuario.getNumDocumento(),
                    "",
                    "",
                    ""+usuario.getTipoUsuario(),
                    ""+usuario.getNombreUsuario(),
                    ""+usuario.getContrasena()
            );
            ack=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ack;
    }

    @Override
    public Usuario obtenerUsuario(String nUser, String pass) {
        return Consulta.obtenerUsuarioPorNombre(nUser,pass);
    }

    public LinkedList<ProductoVenta>  informeVentas (String fecha) {
        LinkedList<ProductoVenta> listProductVenta = new LinkedList<>();
        try {

            listProductVenta = Consulta.obtenerVentasPorProducto(fecha);

        } catch (Exception e){
        e.printStackTrace();
        }
        return listProductVenta;
    }

    public Vendedor informeVendedorMes() {

        return Consulta.obtenerVendedorMes();

    }


}